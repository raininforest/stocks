package com.github.raininforest.stocks

import android.app.Application
import com.github.raininforest.stocks.di.AppComponent
import com.github.raininforest.stocks.di.DaggerAppComponent

/**
 * Application class
 *
 * Created by Sergey Velesko on 31.07.2021
 */
class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent
            .builder()
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}