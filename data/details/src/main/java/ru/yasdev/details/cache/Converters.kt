package ru.yasdev.details.cache

import androidx.room.TypeConverter

object Converters {

    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",").map { it.trim() }
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        return list.joinToString(",")
    }

}
