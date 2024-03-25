package ru.yasdev.weather

import android.location.Location
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import ru.yasdev.common.CommonConstants
import ru.yasdev.weather.mappers.toWeatherModel
import ru.yasdev.weather.models.WeatherState
import ru.yasdev.weather.models.WeatherDTO


class WeatherDataSourceImpl(private val client: HttpClient): WeatherDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getWeather(location: Location?): WeatherState {
        if (location != null) {
            return try {
                val response: HttpResponse =
                    client.get("https://api.openweathermap.org/data/2.5/weather?lat=${location.latitude}&lon=${location.longitude}&appid=${CommonConstants.WEATHER_API_KEY}&lang=ru&units=metric")
                val jsonString: String = response.bodyAsText()
                val weatherDto: WeatherDTO = json.decodeFromString<WeatherDTO>(string = jsonString)

                weatherDto.toWeatherModel()

            } catch (e: Exception) {
                WeatherState.ErrorOnReceipt
            }

        } else {
            return WeatherState.ErrorOnReceipt
        }
    }
}