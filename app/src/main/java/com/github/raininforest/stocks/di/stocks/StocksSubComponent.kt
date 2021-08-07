package com.github.raininforest.stocks.di.stocks

import com.github.raininforest.stocks.di.news.NewsSubComponent
import com.github.raininforest.stocks.di.stocks.module.StocksModule
import com.github.raininforest.stocks.mvp.presenter.StocksPresenter
import com.github.raininforest.stocks.ui.adapter.StocksAdapter
import dagger.Subcomponent

/**
 * Created by Sergey Velesko on 07.08.2021
 */
@StocksScope
@Subcomponent(
    modules = [ StocksModule::class ]
)
interface StocksSubComponent {

    fun newsSubComponent(): NewsSubComponent

    fun inject(stocksPresenter: StocksPresenter)
    fun inject(stocksAdapter: StocksAdapter)
}