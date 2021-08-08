package com.github.raininforest.stocks.mvp.model.entity.room.dao

import androidx.room.*
import com.github.raininforest.stocks.mvp.model.entity.room.RoomNewsEntity

/**
 * Created by Sergey Velesko on 01.08.2021
 */
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: RoomNewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg news: RoomNewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<RoomNewsEntity>)

    @Update
    fun update(news: RoomNewsEntity)

    @Update
    fun update(vararg news: RoomNewsEntity)

    @Update
    fun update(news: List<RoomNewsEntity>)

    @Delete
    fun delete(news: RoomNewsEntity)

    @Delete
    fun delete(vararg news: RoomNewsEntity)

    @Delete
    fun delete(news: List<RoomNewsEntity>)

    @Query("SELECT * FROM RoomNewsEntity")
    fun getAll(): List<RoomNewsEntity>

    @Query("SELECT * FROM RoomNewsEntity WHERE tickerId = :ticker")
    fun findByTicker(ticker: String): List<RoomNewsEntity>
}