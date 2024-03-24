package ru.yasdev.details.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import ru.yasdev.common.CommonConstants
import ru.yasdev.details.cache.CacheStorage
import ru.yasdev.details.cache.mappers.toDetails
import ru.yasdev.details.models.DetailsDTO
import ru.yasdev.details.models.DetailsState
import ru.yasdev.details.models.PhotoDTO

class DetailsDataSource(private val client: HttpClient, private val cacheStorage: CacheStorage) {

    private val json = Json { ignoreUnknownKeys = true }
    suspend fun getDetails(id: String): DetailsState {
        return try {
            val response = client.get("https://api.foursquare.com/v3/places/${id}") {
                url {
                    headers.append("accept", "application/json")
                    headers.append("Authorization", CommonConstants.FOURSQUARE_API_KEY)
                    headers.append("Accept-Language", "ru")
                }
            }
            val jsonString: String = response.bodyAsText()
            val detailsDTO: DetailsDTO = json.decodeFromString<DetailsDTO>(string = jsonString)
            val photoResponse =
                client.get("https://api.foursquare.com/v3/places/${detailsDTO.fsqId}/photos") {
                    url {
                        headers.append("accept", "application/json")
                        headers.append("Authorization", CommonConstants.FOURSQUARE_API_KEY)
                    }
                }

            val photoDTOList: List<PhotoDTO> =
                json.decodeFromString<List<PhotoDTO>>(string = photoResponse.bodyAsText())
            cacheStorage.insertCache(
                detailsDTO.toDetails(photoDTOList)
            )
            detailsDTO.toDetails(photoDTOList)
        } catch (e: Exception) {
            DetailsState.ErrorOnReceipt
        }
    }
}