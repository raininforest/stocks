package com.github.raininforest.stocks.mvp.model.cache

import com.github.raininforest.stocks.mvp.model.entity.News
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Created by Sergey Velesko on 01.08.2021
 */
interface INewsCache {
    fun getNews(ticker: String): Single<List<News>>
    fun putNews(ticker: String, news: List<News>): Completable
}