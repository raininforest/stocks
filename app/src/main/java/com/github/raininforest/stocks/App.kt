package com.github.raininforest.stocks

import android.app.Application
import com.github.raininforest.stocks.di.AppComponent
import com.github.raininforest.stocks.di.DaggerAppComponent
import com.github.raininforest.stocks.di.INewsScopeContainer
import com.github.raininforest.stocks.di.IStocksScopeContainer
import com.github.raininforest.stocks.di.module.AppModule
import com.github.raininforest.stocks.di.news.NewsSubComponent
import com.github.raininforest.stocks.di.stocks.StocksSubComponent

/**
 * Application class
 *
 * Created by Sergey Velesko on 31.07.2021
 */
class App : Application(), IStocksScopeContainer, INewsScopeContainer {

    lateinit var appComponent: AppComponent

    var stocksSubcomponent: StocksSubComponent? = null
        private set

    var newsSubcomponent: NewsSubComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }

    fun initStocksSubComponent() = appComponent

    override fun releaseStocksScope() {
        stocksSubcomponent = null
    }

    override fun releaseNewsScope() {
        newsSubcomponent = null
    }
}