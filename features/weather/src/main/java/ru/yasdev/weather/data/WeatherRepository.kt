package ru.yasdev.weather.data

import android.location.Location
import kotlinx.coroutines.flow.Flow
import ru.yasdev.weather.models.WeatherState

interface WeatherRepository {
    fun getWeather(location: Location?): Flow<WeatherState>
}