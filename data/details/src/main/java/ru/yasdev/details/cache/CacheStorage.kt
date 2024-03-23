package ru.yasdev.details.cache

import android.content.Context
import android.util.Log
import androidx.room.Room
import ru.yasdev.details.cache.mappers.toCacheEntity
import ru.yasdev.details.cache.mappers.toDetails
import ru.yasdev.details.models.DetailsState

class CacheStorage(context: Context) {

    private val db =
        Room.databaseBuilder(
            context,
            CacheDataBase::class.java,
            "cache.db"
        ).build()

    suspend fun getCache(id: String): DetailsState.Details?{
        return try {
            val cache = db.dao.get(id)
            cache?.toDetails()
        }
        catch (e: Exception){
            null
        }
    }

    suspend fun insertCache(details: DetailsState.Details){
        try {
            db.dao.insert(details.toCacheEntity())
        }
        catch (e: Exception){
        }

    }


}