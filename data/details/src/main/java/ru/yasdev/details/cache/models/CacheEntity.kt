package ru.yasdev.details.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CacheEntity(
    @PrimaryKey val id: String,
    val title: String,
    val address: String,
    val photoList: List<String>,
    val categories: List<String>,
)