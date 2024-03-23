package ru.yasdev.auth.current_user_id

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.yasdev.auth.dataBase.AuthDataBase

class GetUserIdDataSource(private val context: Context) {

    private val db =
        Room.databaseBuilder(
            context,
            AuthDataBase::class.java,
            "auth.db"
        ).build()

    suspend fun getId(): String?{
        val userIdEntity = db.userIdDao.getId()
        return userIdEntity?.id
    }
}