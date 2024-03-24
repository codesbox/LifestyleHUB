package ru.yasdev.recommendations_feed

import android.location.Location
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import org.json.JSONObject
import ru.yasdev.common.CommonConstants
import ru.yasdev.recommendations_feed.mappers.toRecommendationModel
import ru.yasdev.recommendations_feed.models.PhotoDTO
import ru.yasdev.recommendations_feed.models.RecommendationDTO
import ru.yasdev.recommendations_feed.models.RecommendationModel
import ru.yasdev.recommendations_feed.models.RecommendationsFeedState

class RecommendationsFeedDataSource(private val client: HttpClient) {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getRecommendationsFeed(
        location: Location?, url: String?
    ): RecommendationsFeedState {
        if (location != null) {
            return try {
                val baseUrl =
                    "https://api.foursquare.com/v3/places/search?ll${location.latitude},${location.longitude}&radius=20000&limit=10"
                val list = mutableListOf<RecommendationModel>()
                val response = client.get(url ?: baseUrl) {
                    url {
                        headers.append("accept", "application/json")
                        headers.append("Authorization", CommonConstants.FOURSQUARE_API_KEY)
                        headers.append("Accept-Language", "ru")
                    }
                }
                val headerValue = response.headers["link"]
                val parts = headerValue?.split("[<>]".toRegex())
                val jsonString: String = response.bodyAsText()
                val results = JSONObject(jsonString).get("results").toString()
                val recommendationDtoList: List<RecommendationDTO> =
                    json.decodeFromString<List<RecommendationDTO>>(string = results)
                for (recommendation in recommendationDtoList) {
                    val photoResponse =
                        client.get("https://api.foursquare.com/v3/places/${recommendation.fsqId}/photos") {
                            url {
                                headers.append("accept", "application/json")
                                headers.append("Authorization", CommonConstants.FOURSQUARE_API_KEY)
                            }
                        }
                    val photoDTOList: List<PhotoDTO> =
                        json.decodeFromString<List<PhotoDTO>>(string = photoResponse.bodyAsText())
                    list.add(
                        recommendation.toRecommendationModel(photoDTOList)
                    )

                }
                RecommendationsFeedState.Model(list, parts?.get(1))
            } catch (e: Exception) {
                RecommendationsFeedState.ErrorOnReceipt
            }

        } else {
            return RecommendationsFeedState.ErrorOnReceipt
        }
    }

}