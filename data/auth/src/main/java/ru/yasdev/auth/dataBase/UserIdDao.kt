package ru.yasdev.auth.dataBase

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ru.yasdev.auth.dataBase.models.UserIdEntity

@Dao
interface UserIdDao {


    @Query("SELECT * FROM current_user LIMIT 1")
    suspend fun getId(): UserIdEntity?

    @Upsert
    suspend fun insertId(model: UserIdEntity)

    @Query("DELETE FROM current_user")
    suspend fun deleteAll()


}