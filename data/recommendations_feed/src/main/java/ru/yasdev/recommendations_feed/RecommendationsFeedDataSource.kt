package ru.yasdev.recommendations_feed

import android.location.Location
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import ru.yasdev.recommendations_feed.models.RecommendationsFeedState

class RecommendationsFeedDataSource(private val client: HttpClient) {

    private val API_KEY = "fsq3/7zKkXDxUnycG2la3H64cnY46t4U0qoKznWy+ub2+A8="

    suspend fun getRecommendationsFeed(location: Location?): RecommendationsFeedState {
        if (location != null){
            return try{
                val response = client.get("https://api.foursquare.com/v3/places/search"){
                    url{
                        parameters.append("ll", "${location.latitude},${location.longitude}")
                        headers.append("accept", "application/json")
                        headers.append("Authorization", API_KEY)
                    }
                }
                val jsonString: String = response.bodyAsText()
                println(jsonString)
                RecommendationsFeedState.Model(emptyList())
            } catch (e: Exception){
                RecommendationsFeedState.ErrorOnReceipt
            }

        }else{
            return RecommendationsFeedState.ErrorOnReceipt
        }





    }

}