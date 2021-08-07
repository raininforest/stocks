package com.github.raininforest.stocks.mvp.model.api.dto

data class NewsDTO(
    val headline: String,
    val summary: String,
    val source: String,
    val url: String
)