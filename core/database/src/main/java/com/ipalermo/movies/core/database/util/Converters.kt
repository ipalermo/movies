package com.ipalermo.movies.core.database.util

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDate? {
        return value?.let {  LocalDate.parse(value) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): String? {
        return date.toString()
    }
}


