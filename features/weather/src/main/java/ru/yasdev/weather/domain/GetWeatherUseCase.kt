package ru.yasdev.weather.domain

import android.location.Location
import ru.yasdev.weather.data.WeatherRepository
import ru.yasdev.weather.models.WeatherState

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun execute(location: Location?): WeatherState = weatherRepository.getWeather(location)


}