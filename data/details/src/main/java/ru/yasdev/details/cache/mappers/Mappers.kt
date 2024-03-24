package ru.yasdev.details.cache.mappers

import ru.yasdev.details.cache.models.CacheEntity
import ru.yasdev.details.models.DetailsDTO
import ru.yasdev.details.models.DetailsState
import ru.yasdev.details.models.PhotoDTO

fun DetailsState.Details.toCacheEntity(): CacheEntity {
    return CacheEntity(
        title = title, categories = categories, id = id, address = address, photoList = photoList
    )
}

fun CacheEntity.toDetails(): DetailsState.Details {
    return DetailsState.Details(
        title = title, categories = categories, id = id, address = address, photoList = photoList
    )
}

fun DetailsDTO.toDetails(photoDTOList: List<PhotoDTO>): DetailsState.Details{
    return DetailsState.Details(
        title = name,
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