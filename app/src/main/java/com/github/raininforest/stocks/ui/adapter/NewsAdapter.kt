package com.github.raininforest.stocks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.raininforest.stocks.databinding.ItemNewsBinding
import com.github.raininforest.stocks.mvp.presenter.list.INewsListItemPresenter
import com.github.raininforest.stocks.mvp.view.list.NewsItemView

/**
 * Created by Sergey Velesko on 31.07.2021
 */
class NewsAdapter(val presenter: INewsListItemPresenter) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            .apply {
                itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
            }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class ViewHolder(val vb: ItemNewsBinding) : RecyclerView.ViewHolder(vb.root),
        NewsItemView {

        override var pos = -1

        override fun setHeader(text: String) = with(vb) {
            newsHeaderTextView.text = text
        }

        override fun setSummary(text: String) = with(vb) {
            newsSummaryTextView.text = text
        }

        override fun setSource(text: String) = with(vb) {
            newsSourceTextView.text = text
        }
    }
}