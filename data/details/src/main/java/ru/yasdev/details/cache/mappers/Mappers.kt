package ru.yasdev.details.cache.mappers

import ru.yasdev.details.cache.models.CacheEntity
import ru.yasdev.details.models.DetailsState

fun DetailsState.Details.toCacheEntity(): CacheEntity{
    return CacheEntity(
        title = title,
        categories = categories,
        id = id,
        address = address,
        photoList = photoList
    )
}

fun CacheEntity.toDetails(): DetailsState.Details{
    return DetailsState.Details(
        title = title,
        categories = categories,
        id = id,
        address = address,
        photoList = photoList
    )
}