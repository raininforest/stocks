package com.github.raininforest.stocks.di

import com.github.raininforest.stocks.di.module.ApiModule
import com.github.raininforest.stocks.di.module.AppModule
import com.github.raininforest.stocks.di.module.CiceroneModule
import com.github.raininforest.stocks.di.module.DataBaseModule
import com.github.raininforest.stocks.di.stocks.StocksSubComponent
import com.github.raininforest.stocks.mvp.presenter.MainPresenter
import com.github.raininforest.stocks.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Sergey Velesko on 31.07.2021
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        DataBaseModule::class,
        ApiModule::class
    ]
)
interface AppComponent {

    fun stocksSubComponent(): StocksSubComponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}