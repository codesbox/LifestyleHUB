package ru.yasdev.auth.current_user_id

import android.content.Context
import androidx.room.Room
import ru.yasdev.auth.Constants
import ru.yasdev.auth.dataBase.AuthDataBase
import ru.yasdev.auth.dataBase.models.UserIdEntity

class SaveIdDataSource(context: Context) {

    private val db = Room.databaseBuilder(
        context, AuthDataBase::class.java, Constants.DB_NAME
    ).build()

    suspend fun saveId(id: String?) {
        when (id) {
            null -> {
                db.userIdDao.deleteAll()
            }
            else -> {
                db.userIdDao.deleteAll()
                db.userIdDao.insertId(UserIdEntity(id))
            }
        }
    }
}