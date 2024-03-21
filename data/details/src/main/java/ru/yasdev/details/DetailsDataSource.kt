package ru.yasdev.details

import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import ru.yasdev.details.models.DetailsModel

class DetailsDataSource(private val client: HttpClient) {

    private val API_KEY = "fsq3/7zKkXDxUnycG2la3H64cnY46t4U0qoKznWy+ub2+A8="
    private val json = Json { ignoreUnknownKeys = true }
    suspend fun getDetails(id: String): DetailsModel{
        return DetailsModel("hgjdfh")


    }
}