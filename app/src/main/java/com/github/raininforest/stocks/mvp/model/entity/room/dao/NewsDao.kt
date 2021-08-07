package com.github.raininforest.stocks.mvp.model.entity.room.dao

import androidx.room.*
import com.github.raininforest.stocks.mvp.model.entity.room.RoomNewsEntity

/**
 * Created by Sergey Velesko on 01.08.2021
 */
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomNewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomNewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomNewsEntity>)

    @Update
    fun update(user: RoomNewsEntity)

    @Update
    fun update(vararg users: RoomNewsEntity)

    @Update
    fun update(users: List<RoomNewsEntity>)

    @Delete
    fun delete(user: RoomNewsEntity)

    @Delete
    fun delete(vararg users: RoomNewsEntity)

    @Delete
    fun delete(users: List<RoomNewsEntity>)

    @Query("SELECT * FROM RoomNewsEntity")
    fun getAll(): List<RoomNewsEntity>

    @Query("SELECT * FROM RoomNewsEntity WHERE tickerId = :ticker LIMIT 1")
    fun findByTicker(ticker: String): List<RoomNewsEntity>
}