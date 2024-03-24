package ru.yasdev.details.remote

import ru.yasdev.details.cache.CacheStorage
import ru.yasdev.details.data.DetailsRepository
import ru.yasdev.details.models.DetailsState

class DetailsRepositoryImpl(
    private val dataSource: DetailsDataSource, private val cacheStorage: CacheStorage
) : DetailsRepository {

    override suspend fun getDetails(id: String): DetailsState {
        val cache = cacheStorage.getCache(id)
        return cache ?: dataSource.getDetails(id)
    }

}