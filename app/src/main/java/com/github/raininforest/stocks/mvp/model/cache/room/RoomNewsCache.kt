package com.github.raininforest.stocks.mvp.model.cache.room

import com.github.raininforest.stocks.mvp.model.cache.INewsCache
import com.github.raininforest.stocks.mvp.model.entity.News
import com.github.raininforest.stocks.mvp.model.entity.room.db.Database
import com.github.raininforest.stocks.mvp.model.toNewsList
import com.github.raininforest.stocks.mvp.model.toRoomNewsEntityList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

/**
 * Created by Sergey Velesko on 01.08.2021
 */
class RoomNewsCache(val db: Database) : INewsCache {
    override fun getNews(ticker: String): Single<List<News>> =
        Single.fromCallable {
            val roomStockEntity = db.stocksDao.findTicker(ticker)
                ?: throw RuntimeException("No ticker in cache: $ticker")
            return@fromCallable db.newsDao.findByTicker(roomStockEntity.ticker).toNewsList()
        }.subscribeOn(Schedulers.io())

    override fun putNews(ticker: String, news: List<News>): Completable =
        Completable.fromAction {
            val roomStockEntity = db.stocksDao.findTicker(ticker)
                ?: throw RuntimeException("No ticker in cache: $ticker")
            db.newsDao.insert(news.toRoomNewsEntityList(ticker))
        }.subscribeOn(Schedulers.io())
}