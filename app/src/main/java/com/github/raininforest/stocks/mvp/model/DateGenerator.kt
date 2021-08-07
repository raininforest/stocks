package com.github.raininforest.stocks.mvp.model

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Sergey Velesko on 07.08.2021
 */
private const val DATE_PATTERN = "yyyy-MM-dd"
private const val MILLISECONDS_IN_WEEK = 604800000L

class DateGenerator {
    private val simpleDateFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())

    fun today(): String = simpleDateFormat.format(Date())
    fun weekAgo(): String = simpleDateFormat.format(
        Date().time - MILLISECONDS_IN_WEEK
    )
}
