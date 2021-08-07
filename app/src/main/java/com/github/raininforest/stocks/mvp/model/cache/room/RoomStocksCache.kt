package com.github.raininforest.stocks.mvp.model.cache.room

import com.github.raininforest.stocks.mvp.model.cache.IStocksCache
import com.github.raininforest.stocks.mvp.model.entity.Stock
import com.github.raininforest.stocks.mvp.model.entity.room.db.Database
import com.github.raininforest.stocks.mvp.model.toRoomStockEntityList
import com.github.raininforest.stocks.mvp.model.toStockList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Sergey Velesko on 01.08.2021
 */
class RoomStocksCache(val db: Database) : IStocksCache {
    override fun getStocks(): Single<List<Stock>> =
        Single.fromCallable {
            db.stocksDao.getAll().toStockList()
        }.subscribeOn(Schedulers.io())


    override fun putStocks(stocks: List<Stock>): Completable =
        Completable.fromAction {
            db.stocksDao.insert(stocks.toRoomStockEntityList())
        }.subscribeOn(Schedulers.io())
}