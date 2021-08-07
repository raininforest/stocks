package com.github.raininforest.stocks.mvp.presenter.list

/**
 * Created by Sergey Velesko on 31.07.2021
 */
interface IListItemPresenter<V> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}