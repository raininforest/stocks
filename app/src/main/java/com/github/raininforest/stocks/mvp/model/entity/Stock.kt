package com.github.raininforest.stocks.mvp.model.entity

/**
 * Created by Sergey Velesko on 01.08.2021
 */
data class Stock(
    val ticker: String,
    val companyName: String,
    val price: String,
    val change: String,
    val logoUrl: String
)
