package com.github.raininforest.stocks.mvp.model.repo

import com.github.raininforest.stocks.mvp.model.api.IRemoteApi
import com.github.raininforest.stocks.mvp.model.api.dto.StockDTO
import com.github.raininforest.stocks.mvp.model.cache.IStocksCache
import com.github.raininforest.stocks.mvp.model.entity.Stock
import com.github.raininforest.stocks.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Sergey Velesko on 01.08.2021
 */
class StocksRepository(
    val api: IRemoteApi,
    val networkStatus: INetworkStatus,
    val cache: IStocksCache
) : IStocksRepository {

    override fun getStocks(): Single<List<Stock>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.stockList()
                    .flatMap { requestStockInfoByTickerList(it) }
            } else {
                cache.getStocks()
            }
        }.subscribeOn(Schedulers.io())

    private fun requestStockInfoByTickerList(listStockDTO: List<StockDTO>): Single<List<Stock>> =
        Single.create { emitter ->
            val resultList = mutableListOf<Stock>()
            listStockDTO.subList(0, 10) //because api has no pagination and list is huge
                .forEach { stockDTO ->
                    resultList.add(requestEachStockInfo(stockDTO)
                        .blockingGet())
                }
            emitter.onSuccess(resultList)
        }

    private fun requestEachStockInfo(stockDTO: StockDTO): Single<Stock> =
        Single.zip(
            api.companyProfile(stockDTO.symbol),
            api.quote(stockDTO.symbol),
            { p1, p2 ->
                Stock(
                    ticker = p1.ticker,
                    companyName = p1.name,
                    price = "${p2.c.toString()} ${p1.currency}",
                    change = p2.dp.toString(),
                    logoUrl = p1.logo
                )
            }
        )
}