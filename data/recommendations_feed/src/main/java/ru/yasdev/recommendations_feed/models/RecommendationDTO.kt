package ru.yasdev.recommendations_feed.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendationDTO(
    val name: String = "",
    val fsq_id: String,
    val categories: List<RecommendationCategory> = emptyList(),
    val location: RecommendationLocation = RecommendationLocation("")

)

@Serializable
data class RecommendationCategory(
    val name: String = ""
)
@Serializable
data class RecommendationLocation(
    val formatted_address: String = ""
)