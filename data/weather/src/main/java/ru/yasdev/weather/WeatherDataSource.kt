package ru.yasdev.weather

import android.location.Location
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import ru.yasdev.weather.models.Weather
import ru.yasdev.weather.models.WeatherDTO
import kotlin.math.roundToInt


class WeatherDataSource(private val client: HttpClient) {

    private val API_KEY = "82903ac09d33883828290c9bb4b240af"

    private val json = Json { ignoreUnknownKeys = true }

    fun getWeather(location: Location?) = flow {
        if (location != null) {
            try{
                val response: HttpResponse =
                    client.get("https://api.openweathermap.org/data/2.5/weather?lat=${location.latitude}&lon=${location.longitude}&appid=$API_KEY&lang=ru&units=metric")
                val jsonString: String = response.bodyAsText()
                val weatherDto: WeatherDTO = json.decodeFromString<WeatherDTO>(string = jsonString)
                emit(Weather.Model(
                    temp = weatherDto.main.temp.roundToInt().toString(),
                    minTemp = weatherDto.main.minTemp.roundToInt().toString(),
                    maxTemp = weatherDto.main.maxTemp.roundToInt().toString(),
                    feelLike = weatherDto.main.feelsLike.roundToInt().toString(),
                    title = weatherDto.weather[0].title,
                    city = weatherDto.city,
                    icon = "https://openweathermap.org/img/wn/${weatherDto.weather[0].icon}@2x.png"
                ))
            }
            catch (e: Exception){
                emit(Weather.ErrorOnReceipt)
            }

        } else {
            emit(Weather.ErrorOnReceipt)
        }

    }


}