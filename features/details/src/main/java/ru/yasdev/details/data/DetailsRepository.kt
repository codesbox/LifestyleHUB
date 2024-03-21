package ru.yasdev.details.data

import ru.yasdev.details.models.DetailsModel

interface DetailsRepository {

    suspend fun getDetails(id: String): DetailsModel

}