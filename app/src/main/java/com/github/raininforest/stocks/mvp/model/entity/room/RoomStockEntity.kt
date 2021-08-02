package com.github.raininforest.stocks.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Sergey Velesko on 01.08.2021
 */
@Entity
data class RoomStockEntity(
    @PrimaryKey
    val ticker: String,
    val companyName: String,
    val price: String,
    val change: String,
    val logoUrl: String
)
