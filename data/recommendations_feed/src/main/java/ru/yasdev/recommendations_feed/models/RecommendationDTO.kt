package ru.yasdev.recommendations_feed.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendationDTO(
    val name: String = "",
    @SerialName("fsq_id") val fsqId: String,
    val categories: List<RecommendationCategory> = emptyList(),
    val location: RecommendationLocation = RecommendationLocation("")

)

@Serializable
data class RecommendationCategory(
    val name: String = ""
)

@Serializable
data class RecommendationLocation(
    @SerialName("formatted_address") val formattedAddress: String = ""
)