package ru.yasdev.details.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailsDTO(
    val name: String = "",
    @SerialName("fsq_id") val fsqId: String,
    val categories: List<Category> = emptyList(),
    val location: PlaceLocation = PlaceLocation("")
)

@Serializable
data class Category(
    val name: String = ""
)

@Serializable
data class PlaceLocation(
    @SerialName("formatted_address") val formattedAddress: String = ""
)