package com.github.raininforest.stocks.di.stocks.module

import com.github.raininforest.stocks.App
import com.github.raininforest.stocks.di.IStocksScopeContainer
import com.github.raininforest.stocks.di.stocks.StocksScope
import com.github.raininforest.stocks.mvp.model.api.IRemoteApi
import com.github.raininforest.stocks.mvp.model.cache.IStocksCache
import com.github.raininforest.stocks.mvp.model.cache.room.RoomStocksCache
import com.github.raininforest.stocks.mvp.model.entity.room.db.StocksDatabase
import com.github.raininforest.stocks.mvp.model.network.INetworkStatus
import com.github.raininforest.stocks.mvp.model.repo.IStocksRepository
import com.github.raininforest.stocks.mvp.model.repo.StocksRepository
import dagger.Module
import dagger.Provides

/**
 * Created by Sergey Velesko on 07.08.2021
 */
@Module
class StocksModule {

    @Provides
    fun stocksCache(database: StocksDatabase): IStocksCache {
        return RoomStocksCache(database)
    }

    @StocksScope
    @Provides
    fun stocksRepo(
        api: IRemoteApi,
        networkStatus: INetworkStatus,
        cache: IStocksCache
    ): IStocksRepository {
        return StocksRepository(api, networkStatus, cache)
    }

    @StocksScope
    @Provides
    fun scopeContainer(app: App): IStocksScopeContainer = app
}