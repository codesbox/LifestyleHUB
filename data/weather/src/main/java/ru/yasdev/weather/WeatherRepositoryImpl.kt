package ru.yasdev.weather

import android.location.Location
import ru.yasdev.weather.data.WeatherRepository

class WeatherRepositoryImpl(private val dataSource: WeatherDataSource) : WeatherRepository {
    override suspend fun getWeather(location: Location?) = dataSource.getWeather(location)
}