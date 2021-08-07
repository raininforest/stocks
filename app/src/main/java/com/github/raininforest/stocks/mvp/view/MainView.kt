package com.github.raininforest.stocks.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Created by Sergey Velesko on 31.07.2021
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView
