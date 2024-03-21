package ru.yasdev.details.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import ru.yasdev.details.cache.CacheStorage
import ru.yasdev.details.models.DetailsDTO
import ru.yasdev.details.models.DetailsState
import ru.yasdev.details.models.PhotoDTO

class DetailsDataSource(private val client: HttpClient, private val cacheStorage: CacheStorage) {

    private val API_KEY = "fsq3/7zKkXDxUnycG2la3H64cnY46t4U0qoKznWy+ub2+A8="
    private val json = Json { ignoreUnknownKeys = true }
    suspend fun getDetails(id: String): DetailsState{
        return try {
            val response = client.get("https://api.foursquare.com/v3/places/${id}") {
                url {
                    headers.append("accept", "application/json")
                    headers.append("Authorization", API_KEY)
                    headers.append("Accept-Language", "ru")
                }
            }
            val jsonString: String = response.bodyAsText()
            val detailsDTO: DetailsDTO = json.decodeFromString<DetailsDTO>(string = jsonString)
            val photoResponse = client.get("https://api.foursquare.com/v3/places/${detailsDTO.fsq_id}/photos"){
                url {
                    headers.append("accept", "application/json")
                    headers.append("Authorization", API_KEY)
                }
            }

            val photoDTOList: List<PhotoDTO> = json.decodeFromString<List<PhotoDTO>>(string = photoResponse.bodyAsText())
            cacheStorage.insertCache(DetailsState.Details(
                title = detailsDTO.name,
                address = detailsDTO.location.formatted_address,
                photoList = photoDTOList.map {
                    val width = if (it.width > 400){
                        400
                    }
                    else{
                        it.width.toInt()
                    }
                    val height = if (it.height > 400){
                        400
                    }
                    else{
                        it.height.toInt()
                    }
                    "${it.prefix}${width}x${height}${it.suffix}"
                },
                categories = detailsDTO.categories.map{ it.name },
                id = detailsDTO.fsq_id
            ))
            DetailsState.Details(
                title = detailsDTO.name,
                address = detailsDTO.location.formatted_address,
                photoList = photoDTOList.map {
                    val width = if (it.width > 400){
                        400
                    }
                    else{
                        it.width.toInt()
                    }
                    val height = if (it.height > 400){
                        400
                    }
                    else{
                        it.height.toInt()
                    }
                    "${it.prefix}${width}x${height}${it.suffix}"
                },
                categories = detailsDTO.categories.map{ it.name },
                id = detailsDTO.fsq_id
            )
        }
        catch (e: Exception){
            DetailsState.ErrorOnReceipt
        }

    }
}