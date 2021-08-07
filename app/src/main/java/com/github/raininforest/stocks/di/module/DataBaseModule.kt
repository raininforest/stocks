package com.github.raininforest.stocks.di.module

import androidx.room.Room
import com.github.raininforest.stocks.App
import com.github.raininforest.stocks.mvp.model.entity.room.db.StocksDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Sergey Velesko on 31.07.2021
 */
@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun database(app: App): StocksDatabase =
        Room.databaseBuilder(app, StocksDatabase::class.java, StocksDatabase.DB_NAME).build()
}