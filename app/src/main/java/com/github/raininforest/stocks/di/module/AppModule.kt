package com.github.raininforest.stocks.di.module

import com.github.raininforest.stocks.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

/**
 * Created by Sergey Velesko on 07.08.2021
 */
@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}