package ru.yasdev.auth.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.yasdev.auth.dataBase.models.AuthEntity

@Database(
    entities = [AuthEntity::class],
    version = 1
)
abstract class AuthDataBase: RoomDatabase() {
    abstract val dao: AuthDao
}