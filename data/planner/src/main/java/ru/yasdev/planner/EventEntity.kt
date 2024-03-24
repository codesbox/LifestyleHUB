package ru.yasdev.planner

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_entity")
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val eventId: Int = 0,
    val title: String,
    val note: String?,
    val link: String?,
    val date: String,
    val userId: String
)
