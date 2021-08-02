package com.github.raininforest.stocks.mvp.model.repo

import com.github.raininforest.stocks.mvp.model.entity.Stock
import io.reactivex.rxjava3.core.Single

/**
 * Created by Sergey Velesko on 01.08.2021
 */
interface IStocksRepository {
    fun getStocks(): Single<List<Stock>>
}