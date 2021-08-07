package com.github.raininforest.stocks.mvp.presenter

import com.github.raininforest.stocks.mvp.model.navigation.IScreens
import com.github.raininforest.stocks.mvp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

/**
 * Created by Sergey Velesko on 31.07.2021
 */
class MainPresenter : MvpPresenter<MainView>() {
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.stocks())
    }

    fun backClicked() {
        router.exit()
    }
}