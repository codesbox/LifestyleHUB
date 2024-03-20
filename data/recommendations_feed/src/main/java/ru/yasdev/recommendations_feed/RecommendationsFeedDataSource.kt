package ru.yasdev.recommendations_feed

import android.location.Location
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.append
import kotlinx.serialization.json.Json
import org.json.JSONArray
import org.json.JSONObject
import ru.yasdev.recommendations_feed.models.PhotoDTO
import ru.yasdev.recommendations_feed.models.RecommendationDTO
import ru.yasdev.recommendations_feed.models.RecommendationModel
import ru.yasdev.recommendations_feed.models.RecommendationsFeedState

class RecommendationsFeedDataSource(private val client: HttpClient) {

    private val API_KEY = "fsq3/7zKkXDxUnycG2la3H64cnY46t4U0qoKznWy+ub2+A8="
    private var url: String? = null
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getRecommendationsFeed(
        location: Location?, isRefresh: Boolean
    ): RecommendationsFeedState {
        Log.d("UUUUUUU", url.toString())
        if (isRefresh) {
            url = null
        }
        if (location != null) {
            return try {
                val baseUrl =
                    "https://api.foursquare.com/v3/places/search?ll${location.latitude},${location.longitude}&radius=20000&limit=10"
                val list = mutableListOf<RecommendationModel>()
                val response = client.get(
                    if (url == null) {
                        baseUrl
                    } else {
                        url.toString()
                    }
                ) {
                    url {
                        headers.append("accept", "application/json")
                        headers.append("Authorization", API_KEY)
                        headers.append("Accept-Language", "ru")
                    }
                }
                val headerValue = response.headers["link"]
                val parts = headerValue?.split("[<>]".toRegex())
                url = parts?.get(1)
                Log.d("URL", url.toString())
                val jsonString: String = response.bodyAsText()
                val results = JSONObject(jsonString).get("results").toString()
                val recommendationDtoList: List<RecommendationDTO> = json.decodeFromString<List<RecommendationDTO>>(string = results)
                for (recommendation in recommendationDtoList){
                    val photoResponse = client.get("https://api.foursquare.com/v3/places/${recommendation.fsq_id}/photos"){
                        url {
                            headers.append("accept", "application/json")
                            headers.append("Authorization", API_KEY)
                        }
                    }
                    val photoDTOList: List<PhotoDTO> = json.decodeFromString<List<PhotoDTO>>(string = photoResponse.bodyAsText())
                    list.add(
                        RecommendationModel(
                        title = recommendation.name,
                        address = recommendation.location.formatted_address,
                        photoList = photoDTOList.map {
                            val width = if (it.width > 200){
                                200
                            }
                            else{
                                it.width.toInt()
                            }
                            val height = if (it.height > 200){
                                200
                            }
                            else{
                                it.height.toInt()
                            }
                            "${it.prefix}${width}x${height}${it.suffix}"
                        },
                            categories = recommendation.categories.map{ it.name }
                    )
                    )

                }
                println(list)
                RecommendationsFeedState.Model(list)
            } catch (e: Exception) {
                println(e.message)
                RecommendationsFeedState.ErrorOnReceipt
            }

        } else {
            return RecommendationsFeedState.ErrorOnReceipt
        }


    }

}