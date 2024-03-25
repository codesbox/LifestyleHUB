package ru.yasdev.auth.dataBase

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ru.yasdev.auth.dataBase.models.AuthEntity

@Dao
interface AuthDao {
    @Upsert
    suspend fun insert(model: AuthEntity)

    @Query("SELECT * FROM AuthEntity WHERE login = :login")
    suspend fun getUserByPassword(login: String): AuthEntity?

    @Query("SELECT * FROM AuthEntity WHERE login = :id")
    suspend fun getUserById(id: String): AuthEntity?

}