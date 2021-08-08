package com.github.raininforest.stocks.mvp.view.list

/**
 * Created by Sergey Velesko on 31.07.2021
 */
interface NewsItemView : IItemView {
    fun setHeader(text: String)
    fun setSummary(text: String)
    fun setSource(text: String)
}