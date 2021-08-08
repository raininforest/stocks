package com.github.raininforest.stocks.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.raininforest.stocks.App
import com.github.raininforest.stocks.databinding.FragmentNewsBinding
import com.github.raininforest.stocks.mvp.presenter.NewsPresenter
import com.github.raininforest.stocks.mvp.view.NewsView
import com.github.raininforest.stocks.ui.BackButtonListener
import com.github.raininforest.stocks.ui.adapter.NewsAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class NewsFragment : MvpAppCompatFragment(), NewsView, BackButtonListener {

    companion object {
        private const val ARG_TICKER = "ticker"

        @JvmStatic
        fun newInstance(ticker: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TICKER, ticker)
                }
            }
    }

    private var vb: FragmentNewsBinding? = null

    var adapter: NewsAdapter? = null

    val presenter: NewsPresenter by moxyPresenter {
        val ticker = arguments?.getString(ARG_TICKER) ?: ""
        NewsPresenter(ticker).apply {
            App.instance.initNewsSubComponent()?.inject(this)
        }
    }

    private var ticker: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ticker = it.getString(ARG_TICKER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentNewsBinding.inflate(inflater, container, false)
            .also { vb = it }
            .root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backPressed()

    override fun init() {
        vb?.newsToolbar?.title = ticker
        vb?.newsToolbar?.setNavigationOnClickListener { presenter.backPressed() }

        vb?.newsRecyclerView?.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(
            vb?.newsRecyclerView?.context,
            LinearLayoutManager.VERTICAL
        )
        vb?.newsRecyclerView?.addItemDecoration(dividerItemDecoration)
        adapter = NewsAdapter(presenter.newsListItemPresenter)
        vb?.newsRecyclerView?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun showLoading() {
        vb?.progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        vb?.progressBar?.visibility = View.GONE
    }
}