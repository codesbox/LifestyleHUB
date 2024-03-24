package ru.yasdev.auth.dataBase.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_user")
data class UserIdEntity(
    @PrimaryKey val id: String
)
