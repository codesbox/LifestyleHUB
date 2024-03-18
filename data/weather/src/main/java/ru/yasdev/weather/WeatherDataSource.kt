package ru.yasdev.weather

import android.location.Location
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.yasdev.weather.models.Weather

class WeatherDataSource {

    private val API_KEY = "82903ac09d33883828290c9bb4b240af"

    private val client = HttpClient(OkHttp)

    fun getWeather(location: Location?) = flow {
        if (location != null) {
            val response: HttpResponse =
                client.get("https://api.openweathermap.org/data/2.5/weather?lat=${location.latitude}&lon=${location.longitude}&appid=$API_KEY&lang=ru")
            Log.d("response", response.toString())
            emit(Weather.Model("qqq"))
        } else {
            emit(Weather.ErrorOnReceipt)
        }

    }


}