package ru.yasdev.auth.dataBase.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AuthEntity(
    @PrimaryKey val login: String, val password: String, val firstName: String, val lastName: String
)