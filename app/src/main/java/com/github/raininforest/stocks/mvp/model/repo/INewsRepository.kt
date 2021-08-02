package com.github.raininforest.stocks.mvp.model.repo

import com.github.raininforest.stocks.mvp.model.entity.News
import io.reactivex.rxjava3.core.Single

/**
 * Created by Sergey Velesko on 01.08.2021
 */
interface INewsRepository {
    fun getNews(ticker: String): Single<List<News>>
}