package ru.yasdev.details.models

import kotlinx.serialization.Serializable

@Serializable
data class PhotoDTO(
    val prefix: String, val suffix: String, val width: Double, val height: Double
)