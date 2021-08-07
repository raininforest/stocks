package com.github.raininforest.stocks.mvp.model.api

import com.github.raininforest.stocks.BuildConfig
import com.github.raininforest.stocks.mvp.model.api.dto.CompanyProfileDTO
import com.github.raininforest.stocks.mvp.model.api.dto.NewsDTO
import com.github.raininforest.stocks.mvp.model.api.dto.QuoteDTO
import com.github.raininforest.stocks.mvp.model.api.dto.StockDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sergey Velesko on 31.07.2021
 */
private const val DEFAULT_CURRENCY = "USD"
private const val DEFAULT_EXCHANGE = "US"
private const val API_KEY = BuildConfig.API_KEY

interface IRemoteApi {
    @GET("stock/symbol")
    fun stockList(
        @Query("exchange") exchange: String = DEFAULT_EXCHANGE,
        @Query("currency") currency: String = DEFAULT_CURRENCY,
        @Query("token") apikey: String = API_KEY
    ): Single<List<StockDTO>>

    @GET("quote")
    fun quote(
        @Query("symbol") ticker: String,
        @Query("token") apikey: String = API_KEY
    ): Single<QuoteDTO>

    @GET("stock/profile2")
    fun companyProfile(
        @Query("symbol") ticker: String,
        @Query("token") apikey: String = API_KEY
    ): Single<CompanyProfileDTO>

    @GET("company-news")
    fun news(
        @Query("symbol") ticker: String,
        @Query("from") fromDate: String,
        @Query("to") toDate: String,
        @Query("token") apikey: String = API_KEY
    ): Single<List<NewsDTO>>
}