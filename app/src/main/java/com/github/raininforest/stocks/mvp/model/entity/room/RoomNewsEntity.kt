package com.github.raininforest.stocks.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by Sergey Velesko on 01.08.2021
 */
@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomStockEntity::class,
        parentColumns = ["ticker"],
        childColumns = ["tickerId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomNewsEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val header: String,
    val summary: String,
    val source: String,
    val url: String,
    val tickerId: String
)
