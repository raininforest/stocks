package com.github.raininforest.stocks.mvp.model.api.dto

data class NewsDTO(
    var headline: String = "",
    var summary: String = "",
    var source: String = "",
    var url: String = ""
)