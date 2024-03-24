package ru.yasdev.auth.current_user_id

import android.content.Context
import androidx.room.Room
import ru.yasdev.auth.Constants
import ru.yasdev.auth.dataBase.AuthDataBase

class GetUserIdDataSource(context: Context) {

    private val db = Room.databaseBuilder(
        context, AuthDataBase::class.java, Constants.DB_NAME
    ).build()

    suspend fun getId(): String? {
        val userIdEntity = db.userIdDao.getId()
        return userIdEntity?.id
    }
}