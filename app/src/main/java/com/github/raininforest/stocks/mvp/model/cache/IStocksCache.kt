package com.github.raininforest.stocks.mvp.model.cache

import com.github.raininforest.stocks.mvp.model.entity.Stock
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Created by Sergey Velesko on 01.08.2021
 */
interface IStocksCache {
    fun getStocks(): Single<List<Stock>>
    fun putStocks(stocks: List<Stock>): Completable
}