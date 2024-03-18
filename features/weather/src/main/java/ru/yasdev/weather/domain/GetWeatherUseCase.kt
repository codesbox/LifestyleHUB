package ru.yasdev.weather.domain

import android.location.Location
import kotlinx.coroutines.flow.Flow
import ru.yasdev.weather.data.WeatherRepository
import ru.yasdev.weather.models.Weather

internal class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {

    fun execute(location: Location?): Flow<Weather> = weatherRepository.getWeather(location)


}