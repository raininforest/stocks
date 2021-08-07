package com.github.raininforest.stocks.mvp.view.list

/**
 * Created by Sergey Velesko on 31.07.2021
 */
interface StockItemView : IItemView {
    fun setTicker(ticker: String)
    fun setCompany(company: String)
    fun setPrice(price: String)
    fun setChange(change: String)
    fun setLogo(url: String)
}