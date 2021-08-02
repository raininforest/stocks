package com.github.raininforest.stocks.mvp.model.entity.room.dao

import androidx.room.*
import com.github.raininforest.stocks.mvp.model.entity.room.RoomStockEntity

/**
 * Created by Sergey Velesko on 01.08.2021
 */
interface StocksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomStockEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomStockEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomStockEntity>)

    @Update
    fun update(user: RoomStockEntity)

    @Update
    fun update(vararg users: RoomStockEntity)

    @Update
    fun update(users: List<RoomStockEntity>)

    @Delete
    fun delete(user: RoomStockEntity)

    @Delete
    fun delete(vararg users: RoomStockEntity)

    @Delete
    fun delete(users: List<RoomStockEntity>)

    @Query("SELECT * FROM RoomStockEntity")
    fun getAll(): List<RoomStockEntity>

    @Query("SELECT * FROM RoomStockEntity WHERE ticker = :ticker LIMIT 1")
    fun findByTicker(ticker: String): RoomStockEntity?
}