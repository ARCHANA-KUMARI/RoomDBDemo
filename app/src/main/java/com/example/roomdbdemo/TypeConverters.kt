package com.example.roomdbdemo

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

class TypeConverters {
    @TypeConverter
    fun fromDateToLong(value: Date): Long {
        return value.time
    }

    @TypeConverter
    fun fromLongToDate(value: Long): Date {
        return Date(value)
    }

}