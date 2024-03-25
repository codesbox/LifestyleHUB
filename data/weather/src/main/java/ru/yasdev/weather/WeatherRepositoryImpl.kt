package ru.yasdev.weather

import android.location.Location
import ru.yasdev.weather.data.WeatherRepository

class WeatherRepositoryImpl(private val dataSource: WeatherDataSourceImpl) : WeatherRepository {
    override suspend fun getWeather(location: Location?) = dataSource.getWeather(location)
}