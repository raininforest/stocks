package com.github.raininforest.stocks.mvp.model

import com.github.raininforest.stocks.mvp.model.api.dto.NewsDTO
import com.github.raininforest.stocks.mvp.model.entity.News
import com.github.raininforest.stocks.mvp.model.entity.room.RoomNewsEntity

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

@JvmName("RoomNewsEntityListToNewsList")
fun List<RoomNewsEntity>.toNewsList() = this.map { it.toNews() }

@JvmName("NewsDTOListToNewsList")
fun List<NewsDTO>.toNewsList() = this.map { it.toNews() }

@JvmName("NewsListToRoomNewsEntityList")
fun List<News>.toRoomNewsEntityList(ticker: String) = this.map { it.toRoomNewsEntity(ticker) }