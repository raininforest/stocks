package com.github.raininforest.stocks.di.news.module

import com.github.raininforest.stocks.App
import com.github.raininforest.stocks.di.INewsScopeContainer
import com.github.raininforest.stocks.di.news.NewsScope
import com.github.raininforest.stocks.mvp.model.DateGenerator
import com.github.raininforest.stocks.mvp.model.api.IRemoteApi
import com.github.raininforest.stocks.mvp.model.cache.INewsCache
import com.github.raininforest.stocks.mvp.model.cache.room.RoomNewsCache
import com.github.raininforest.stocks.mvp.model.entity.room.db.StocksDatabase
import com.github.raininforest.stocks.mvp.model.network.INetworkStatus
import com.github.raininforest.stocks.mvp.model.repo.INewsRepository
import com.github.raininforest.stocks.mvp.model.repo.NewsRepository
import dagger.Module
import dagger.Provides

/**
 * Created by Sergey Velesko on 07.08.2021
 */
@Module
class NewsModule {

    @Provides
    fun dateGenerator(): DateGenerator = DateGenerator()

    @Provides
    fun newsCache(database: StocksDatabase): INewsCache {
        return RoomNewsCache(database)
    }

    @NewsScope
    @Provides
    fun newsRepo(
        api: IRemoteApi,
        networkStatus: INetworkStatus,
        cache: INewsCache,
        dateGenerator: DateGenerator
    ): INewsRepository {
        return NewsRepository(api, networkStatus, cache, dateGenerator)
    }

    @NewsScope
    @Provides
    fun scopeContainer(app: App): INewsScopeContainer = app
}