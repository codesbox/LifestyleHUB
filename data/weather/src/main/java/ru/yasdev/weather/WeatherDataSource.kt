package ru.yasdev.weather

import android.location.Location
import ru.yasdev.weather.models.WeatherState

interface WeatherDataSource {
    suspend fun getWeather(location: Location?): WeatherState
}