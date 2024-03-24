package ru.yasdev.recommendations_feed

import android.location.Location
import ru.yasdev.recommendations_feed.data.RecommendationsFeedRepository
import ru.yasdev.recommendations_feed.models.RecommendationsFeedState

class RecommendationsFeedRepositoryImpl(private val dataSource: RecommendationsFeedDataSource) :
    RecommendationsFeedRepository {
    override suspend fun getRecommendationsFeed(
        location: Location?, url: String?
    ): RecommendationsFeedState {
        return dataSource.getRecommendationsFeed(location, url)
    }
}