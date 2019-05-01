package com.volkovmedia.core.data.datasource.database.converter

import androidx.room.TypeConverter
import java.util.*

internal class DateConverter {

    @TypeConverter
    fun fromDate(date: Date) = date.time

    @TypeConverter
    fun fromTimestamp(timestamp: Long) = Date(timestamp)

}