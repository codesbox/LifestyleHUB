package ru.yasdev.weather.data

import android.location.Location
import kotlinx.coroutines.flow.Flow
import ru.yasdev.weather.models.WeatherState

interface WeatherRepository {
    suspend fun getWeather(location: Location?): WeatherState
}