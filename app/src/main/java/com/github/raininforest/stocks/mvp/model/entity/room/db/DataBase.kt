package com.github.raininforest.stocks.mvp.model.entity.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.raininforest.stocks.mvp.model.entity.room.RoomNewsEntity
import com.github.raininforest.stocks.mvp.model.entity.room.RoomStockEntity
import com.github.raininforest.stocks.mvp.model.entity.room.dao.NewsDao
import com.github.raininforest.stocks.mvp.model.entity.room.dao.StocksDao

/**
 * Created by Sergey Velesko on 01.08.2021
 */
@Database(
    entities = [RoomStockEntity::class, RoomNewsEntity::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val stocksDao: StocksDao
    abstract val newsDao: NewsDao

    companion object {
        const val DB_NAME = "database.db"
    }
}