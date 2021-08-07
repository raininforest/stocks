package com.github.raininforest.stocks.mvp.model.api.dto

/**
 * Created by Sergey Velesko on 04.08.2021
 */
data class QuoteDTO(
    var c: String = "0",  //current price
    var d: String = "0",  //change
    var dp: String = "0", //percent change
)