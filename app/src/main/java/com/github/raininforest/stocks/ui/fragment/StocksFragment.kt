package com.github.raininforest.stocks.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.raininforest.stocks.App
import com.github.raininforest.stocks.databinding.FragmentStocksBinding
import com.github.raininforest.stocks.di.stocks.StocksSubComponent
import com.github.raininforest.stocks.mvp.presenter.StocksPresenter
import com.github.raininforest.stocks.mvp.view.StocksView
import com.github.raininforest.stocks.ui.BackButtonListener
import com.github.raininforest.stocks.ui.adapter.StocksAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class StocksFragment : MvpAppCompatFragment(), StocksView, BackButtonListener {

    companion object {
        @JvmStatic
        fun newInstance() = StocksFragment()
    }

    var stocksSubComponent: StocksSubComponent? = null

    val presenter: StocksPresenter by moxyPresenter {
        StocksPresenter().apply {
            stocksSubComponent = App.instance.initStocksSubComponent()
            stocksSubComponent?.inject(this)
        }
    }

    var adapter: StocksAdapter? = null

    private var vb: FragmentStocksBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentStocksBinding.inflate(inflater, container, false)
            .also { vb = it }
            .root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.stockRecyclerView?.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(
            vb?.stockRecyclerView?.context,
            LinearLayoutManager.VERTICAL
        )
        vb?.stockRecyclerView?.addItemDecoration(dividerItemDecoration)
        adapter = StocksAdapter(presenter.stocksListPresenter).apply {
            stocksSubComponent?.inject(this)
        }
        vb?.stockRecyclerView?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}
