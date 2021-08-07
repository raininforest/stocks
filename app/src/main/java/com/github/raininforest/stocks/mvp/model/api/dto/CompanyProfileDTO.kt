package com.github.raininforest.stocks.mvp.model.api.dto

/**
 * Created by Sergey Velesko on 04.08.2021
 */
data class CompanyProfileDTO(
    val currency: String,
    val logo: String,
    val name: String,
    val ticker: String,
)