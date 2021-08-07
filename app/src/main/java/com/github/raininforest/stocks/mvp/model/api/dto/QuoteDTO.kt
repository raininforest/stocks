package com.github.raininforest.stocks.mvp.model.api.dto

/**
 * Created by Sergey Velesko on 04.08.2021
 */
data class QuoteDTO(
    val c: Double,  //current price
    val d: Double,  //change
    val dp: Double, //percent change
)