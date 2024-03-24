package ru.yasdev.recommendations_feed.mappers

import ru.yasdev.recommendations_feed.models.PhotoDTO
import ru.yasdev.recommendations_feed.models.RecommendationDTO
import ru.yasdev.recommendations_feed.models.RecommendationModel

fun RecommendationDTO.toRecommendationModel(photoDTOList: List<PhotoDTO>): RecommendationModel {
    return RecommendationModel(title = name,
        address = location.formattedAddress,
        photoList = photoDTOList.map {
            val width = if (it.width > 400) {
                400
            } else {
                it.width.toInt()
            }
            val height = if (it.height > 400) {
                400
            } else {
                it.height.toInt()
            }
            "${it.prefix}${width}x${height}${it.suffix}"
        },
        categories = categories.map { it.name },
        id = fsqId
    )
}