package ru.yasdev.auth.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.yasdev.auth.dataBase.models.AuthEntity
import ru.yasdev.auth.dataBase.models.UserIdEntity

@Database(
    entities = [AuthEntity::class, UserIdEntity::class], version = 1
)
abstract class AuthDataBase : RoomDatabase() {
    abstract val dao: AuthDao
    abstract val userIdDao: UserIdDao
}