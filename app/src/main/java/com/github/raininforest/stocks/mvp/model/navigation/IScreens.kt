package com.github.raininforest.stocks.mvp.model.navigation

import com.github.terrakok.cicerone.Screen

/**
 * Created by Sergey Velesko on 01.08.2021
 */
interface IScreens {
    fun stocks(): Screen
    fun news(ticker: String): Screen
}