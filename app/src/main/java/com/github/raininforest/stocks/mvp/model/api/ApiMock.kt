package com.github.raininforest.stocks.mvp.model.api

import com.github.raininforest.stocks.mvp.model.api.dto.StockDTO
import io.reactivex.rxjava3.core.Single

/**
 * Created by Sergey Velesko on 07.08.2021
 */
object ApiMock {
    fun stockList(): Single<List<StockDTO>> =
        Single.just(
            listOf(
                StockDTO("AAPL"),
                StockDTO("GM"),
                StockDTO("TSLA"),
                StockDTO("FB"),
                StockDTO("UBER"),
                StockDTO("AMZN"),
                StockDTO("NVDA"),
                StockDTO("BB"),
                StockDTO("NFLX"),
                StockDTO("PYPL"),
                StockDTO("GOOGL"),
                StockDTO("MSFT"),
                StockDTO("BABA"),
            )
        )
}