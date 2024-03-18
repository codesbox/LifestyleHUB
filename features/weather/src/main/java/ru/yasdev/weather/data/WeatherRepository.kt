package ru.yasdev.weather.data

import android.location.Location
import kotlinx.coroutines.flow.Flow
import ru.yasdev.weather.models.Weather

interface WeatherRepository {
    fun getWeather(location: Location?): Flow<Weather>
}