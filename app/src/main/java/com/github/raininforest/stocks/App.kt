package com.github.raininforest.stocks

import android.app.Application

/**
 * Application class
 *
 * Created by Sergey Velesko on 31.07.2021
 */
class App : Application() {

//    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

//        appComponent = DaggerAppComponent
//            .builder()
//            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}