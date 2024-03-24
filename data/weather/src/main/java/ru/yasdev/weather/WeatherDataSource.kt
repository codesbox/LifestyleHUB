package ru.yasdev.weather

import android.location.Location
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import ru.yasdev.common.CommonConstants
import ru.yasdev.weather.mappers.toWeatherModel
import ru.yasdev.weather.models.WeatherState
import ru.yasdev.weather.models.WeatherDTO


class WeatherDataSource(private val client: HttpClient) {

    private val json = Json { ignoreUnknownKeys = true }

    fun getWeather(location: Location?) = flow {
        if (location != null) {
            try {
                val response: HttpResponse =
                    client.get("https://api.openweathermap.org/data/2.5/weather?lat=${location.latitude}&lon=${location.longitude}&appid=${CommonConstants.WEATHER_API_KEY}&lang=ru&units=metric")
                val jsonString: String = response.bodyAsText()
                val weatherDto: WeatherDTO = json.decodeFromString<WeatherDTO>(string = jsonString)
                emit(
                    weatherDto.toWeatherModel()
                )
            } catch (e: Exception) {
                emit(WeatherState.ErrorOnReceipt)
            }

        } else {
            emit(WeatherState.ErrorOnReceipt)
        }
    }
}