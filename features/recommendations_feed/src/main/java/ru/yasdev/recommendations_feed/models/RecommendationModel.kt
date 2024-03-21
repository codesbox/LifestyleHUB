package ru.yasdev.recommendations_feed.models

data class RecommendationModel(
    val title: String,
    val address: String,
    val photoList: List<String>,
    val categories: List<String>,
    val id: String
)
