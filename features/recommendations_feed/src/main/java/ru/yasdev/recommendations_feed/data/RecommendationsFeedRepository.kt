package ru.yasdev.recommendations_feed.data

import android.location.Location
import ru.yasdev.recommendations_feed.models.RecommendationsFeedState

interface RecommendationsFeedRepository {

    suspend fun getRecommendationsFeed(location: Location?, url: String?): RecommendationsFeedState

}