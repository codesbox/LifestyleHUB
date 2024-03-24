package ru.yasdev.details.cache

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ru.yasdev.details.cache.models.CacheEntity

@Dao
interface CacheDao {
    @Upsert
    suspend fun insert(model: CacheEntity)

    @Query("SELECT * FROM CacheEntity WHERE id = :id")
    suspend fun get(id: String): CacheEntity?
}