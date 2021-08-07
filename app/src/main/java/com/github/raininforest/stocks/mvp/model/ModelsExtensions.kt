package com.github.raininforest.stocks.mvp.model

import com.github.raininforest.stocks.mvp.model.api.dto.NewsDTO
import com.github.raininforest.stocks.mvp.model.entity.News
import com.github.raininforest.stocks.mvp.model.entity.Stock
import com.github.raininforest.stocks.mvp.model.entity.room.RoomNewsEntity
import com.github.raininforest.stocks.mvp.model.entity.room.RoomStockEntity

/**
 * Created by Sergey Velesko on 04.08.2021
 */
fun NewsDTO.toNews(): News =
    News(
        header = headline,
        summary = summary,
        source = source,
        url = url
    )

fun News.toRoomNewsEntity(ticker: String): RoomNewsEntity =
    RoomNewsEntity(
        header = header,
        summary = summary,
        source = source,
        url = url,
        tickerId = ticker
    )

fun RoomNewsEntity.toNews(): News =
    News(
        header = header,
        summary = summary,
        source = source,
        url = url
    )

fun RoomStockEntity.toStock(): Stock =
    Stock(
        ticker = ticker,
        companyName = companyName,
        price = price,
        change = change,
        logoUrl = logoUrl
    )

fun Stock.toRoomStockEntity(): RoomStockEntity =
    RoomStockEntity(
        ticker = ticker,
        companyName = companyName,
        price = price,
        change = change,
        logoUrl = logoUrl
    )

@JvmName("RoomNewsEntityListToNewsList")
fun List<RoomNewsEntity>.toNewsList() = this.map { it.toNews() }

@JvmName("NewsDTOListToNewsList")
fun List<NewsDTO>.toNewsList() = this.map { it.toNews() }

@JvmName("NewsListToRoomNewsEntityList")
fun List<News>.toRoomNewsEntityList(ticker: String) = this.map { it.toRoomNewsEntity(ticker) }

@JvmName("RoomStockEntityListToStockList")
fun List<RoomStockEntity>.toStockList() = this.map { it.toStock() }

@JvmName("StockListToRoomStockEntityList")
fun List<Stock>.toRoomStockEntityList() = this.map { it.toRoomStockEntity() }