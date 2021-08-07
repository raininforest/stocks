package com.github.raininforest.stocks.di.module

import com.github.raininforest.stocks.mvp.model.navigation.IScreens
import com.github.raininforest.stocks.ui.navigation.Screens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Sergey Velesko on 31.07.2021
 */
@Module
class CiceroneModule {
    var cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun cicerone(): Cicerone<Router> = cicerone

    @Singleton
    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router(): Router = cicerone.router

    @Singleton
    @Provides
    fun screens(): IScreens = Screens()
}