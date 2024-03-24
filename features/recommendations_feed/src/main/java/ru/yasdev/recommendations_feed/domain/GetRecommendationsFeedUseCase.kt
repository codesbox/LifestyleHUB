package ru.yasdev.recommendations_feed.domain

import android.location.Location
import ru.yasdev.recommendations_feed.data.RecommendationsFeedRepository
import ru.yasdev.recommendations_feed.models.RecommendationsFeedState

class GetRecommendationsFeedUseCase(private val repository: RecommendationsFeedRepository) {

    suspend fun execute(location: Location?, url: String?): RecommendationsFeedState {
        return repository.getRecommendationsFeed(location, url)
    }

}