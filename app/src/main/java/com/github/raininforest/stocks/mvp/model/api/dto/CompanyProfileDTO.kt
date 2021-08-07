package com.github.raininforest.stocks.mvp.model.api.dto

/**
 * Created by Sergey Velesko on 04.08.2021
 */
data class CompanyProfileDTO(
    var currency: String = "",
    var logo: String = "",
    var name: String = "",
    var ticker: String = "",
)