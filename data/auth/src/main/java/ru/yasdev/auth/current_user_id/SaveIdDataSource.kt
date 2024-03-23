package ru.yasdev.auth.current_user_id

import android.content.Context
import androidx.room.Room
import ru.yasdev.auth.dataBase.AuthDataBase
import ru.yasdev.auth.dataBase.models.UserIdEntity

class SaveIdDataSource(private val context: Context) {

    private val db =
        Room.databaseBuilder(
            context,
            AuthDataBase::class.java,
            "auth.db"
        ).build()

    suspend fun saveId(id: String?){
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