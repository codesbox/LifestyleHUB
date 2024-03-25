package ru.yasdev.auth.dataBase.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AuthEntity(
    @PrimaryKey val login: String,
    val hashPassword: String,
    val salt: String,
    val firstName: String,
    val lastName: String,
    val image: String
)