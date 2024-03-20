package ru.yasdev.recommendations_feed.models

sealed interface RecommendationsFeedState {

    data object Loading : RecommendationsFeedState
    data object NoPermissions : RecommendationsFeedState
    data class Model(val list: List<RecommendationModel>, val url: String?) :
        RecommendationsFeedState

    data object ErrorOnReceipt : RecommendationsFeedState
}