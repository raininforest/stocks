package com.github.raininforest.stocks.mvp.view

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Created by Sergey Velesko on 08.08.2021
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface ViewWithLoading {
    fun showLoading()
    fun hideLoading()
}