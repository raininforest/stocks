package com.github.raininforest.stocks.mvp.model.repo

import com.github.raininforest.stocks.mvp.model.api.ApiMock
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
                //api.stockList() //disabled because of null/empty data for most tickers. ApiMock returns popular tickers instead.
                ApiMock.stockList()
                    .flatMap { requestStockInfoByTickerList(it) }
                    .flatMap { cache.putStocks(it).toSingleDefault(it) }
            } else {
                cache.getStocks()
            }
        }.subscribeOn(Schedulers.io())

    private fun requestStockInfoByTickerList(listStockDTO: List<StockDTO>): Single<List<Stock>> =
        Single.create { emitter ->
            val resultList = mutableListOf<Stock>()
            listStockDTO.subList(0, 10) //because api has no pagination and list is huge
                .forEach { stockDTO ->
                    requestEachStockInfo(stockDTO)
                        .blockingSubscribe {
                            resultList.add(it)
                        }
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
                    price = "${p2.c} ${p1.currency}",
                    change = p2.d,
                    logoUrl = p1.logo
                )
            }
        )
}