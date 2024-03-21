package ru.yasdev.details.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ru.yasdev.details.cache.models.CacheEntity
import ru.yasdev.details.models.DetailsState

@Database(
    entities = [CacheEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class CacheDataBase: RoomDatabase() {
    abstract val dao: CacheDao
}