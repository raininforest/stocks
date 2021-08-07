package com.github.raininforest.stocks.ui.navigation

import com.github.raininforest.stocks.mvp.model.navigation.IScreens
import com.github.raininforest.stocks.ui.fragment.NewsFragment
import com.github.raininforest.stocks.ui.fragment.StocksFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 * Created by Sergey Velesko on 31.07.2021
 */
class Screens : IScreens {

    override fun stocks() = FragmentScreen { StocksFragment.newInstance() }

    override fun news(ticker: String) = FragmentScreen { NewsFragment.newInstance(ticker) }
}