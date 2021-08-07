package com.github.raininforest.stocks.mvp.model.entity.room.dao

import androidx.room.*
import com.github.raininforest.stocks.mvp.model.entity.room.RoomStockEntity

/**
 * Created by Sergey Velesko on 01.08.2021
 */
@Dao
interface StocksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stock: RoomStockEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg stocks: RoomStockEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stocks: List<RoomStockEntity>)

    @Update
    fun update(stock: RoomStockEntity)

    @Update
    fun update(vararg stocks: RoomStockEntity)

    @Update
    fun update(stocks: List<RoomStockEntity>)

    @Delete
    fun delete(stock: RoomStockEntity)

    @Delete
    fun delete(vararg stocks: RoomStockEntity)

    @Delete
    fun delete(stocks: List<RoomStockEntity>)

    @Query("SELECT * FROM RoomStockEntity")
    fun getAll(): List<RoomStockEntity>

    @Query("SELECT * FROM RoomStockEntity WHERE ticker = :ticker LIMIT 1")
    fun findTicker(ticker: String): RoomStockEntity?
}