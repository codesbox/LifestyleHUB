package ru.yasdev.details.cache

import android.content.Context
import androidx.room.Room
import ru.yasdev.details.Constants
import ru.yasdev.details.cache.mappers.toCacheEntity
import ru.yasdev.details.cache.mappers.toDetails
import ru.yasdev.details.models.DetailsState

class CacheStorage(context: Context) {

    private val db = Room.databaseBuilder(
        context, CacheDataBase::class.java, Constants.DATABASE_NAME
    ).build()

    suspend fun getCache(id: String): DetailsState.Details? {
        return try {
            val cache = db.dao.get(id)
            cache?.toDetails()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun insertCache(details: DetailsState.Details) {
        db.dao.insert(details.toCacheEntity())
    }
}