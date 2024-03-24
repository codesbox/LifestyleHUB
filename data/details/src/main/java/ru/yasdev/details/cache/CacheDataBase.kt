package ru.yasdev.details.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.yasdev.details.cache.models.CacheEntity

@Database(
    entities = [CacheEntity::class], version = 1
)
@TypeConverters(Converters::class)
abstract class CacheDataBase : RoomDatabase() {
    abstract val dao: CacheDao
}