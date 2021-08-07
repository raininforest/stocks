package com.github.raininforest.stocks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.raininforest.stocks.databinding.ItemStockBinding
import com.github.raininforest.stocks.mvp.presenter.list.IStocksListItemPresenter
import com.github.raininforest.stocks.mvp.view.list.StockItemView

/**
 * Created by Sergey Velesko on 31.07.2021
 */
class StocksAdapter(val presenter: IStocksListItemPresenter) :
    RecyclerView.Adapter<StocksAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemStockBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    inner class ViewHolder(private val vb: ItemStockBinding) : RecyclerView.ViewHolder(vb.root),
        StockItemView {

        override var pos = -1

        override fun setTicker(ticker: String) = with(vb) {
            tickerTextView.text = ticker
        }

        override fun setCompany(company: String) = with(vb) {
            companyTextView.text = company
        }

        override fun setPrice(price: String) = with(vb) {
            priceTextView.text = price
        }

        override fun setChange(change: String) = with(vb) {
            stockChangeTextView.text = change
        }

        override fun setLogo(url: String) {
            with(vb) {
                Glide.with(companyLogo.context)
                    .load(url)
                    .into(companyLogo)
            }
        }

        fun unbind() = with(vb) { companyLogo.setImageDrawable(null) }
    }
}