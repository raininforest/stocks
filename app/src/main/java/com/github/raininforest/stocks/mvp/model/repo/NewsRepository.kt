package com.github.raininforest.stocks.mvp.model.repo

import com.github.raininforest.stocks.mvp.model.DateGenerator
import com.github.raininforest.stocks.mvp.model.api.IRemoteApi
import com.github.raininforest.stocks.mvp.model.cache.INewsCache
import com.github.raininforest.stocks.mvp.model.entity.News
import com.github.raininforest.stocks.mvp.model.network.INetworkStatus
import com.github.raininforest.stocks.mvp.model.toNewsList
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Sergey Velesko on 01.08.2021
 */
class NewsRepository(
    val api: IRemoteApi,
    val networkStatus: INetworkStatus,
    val cache: INewsCache,
    private val dateGenerator: DateGenerator
) : INewsRepository {
    override fun getNews(ticker: String): Single<List<News>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.news(ticker, dateGenerator.weekAgo(), dateGenerator.today())
                    .flatMap { newsDtoList ->
                        cache.putNews(ticker, newsDtoList.toNewsList())
                            .toSingleDefault(newsDtoList.toNewsList())
                    }
            } else {
                cache.getNews(ticker)
            }
        }.subscribeOn(Schedulers.io())
}