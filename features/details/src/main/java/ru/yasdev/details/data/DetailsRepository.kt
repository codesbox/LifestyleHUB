package ru.yasdev.details.data

import ru.yasdev.details.models.DetailsState


interface DetailsRepository {

    suspend fun getDetails(id: String): DetailsState

}