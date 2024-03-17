package ru.yasdev.weather.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.yasdev.weather.models.Weather
import ru.yasdev.weather.models.WeatherEvent

class WeatherViewModel : ViewModel() {

    private val _weatherState = MutableStateFlow<Weather>(Weather.Loading)
    val weatherState = _weatherState.asStateFlow()

    fun onWeatherEvent(weatherEvent: WeatherEvent) {
        when (weatherEvent) {
            WeatherEvent.NoPermissions -> {
                _weatherState.value = Weather.NoPermissions
            }

            WeatherEvent.GetWeather -> {
                getWeather()
            }
        }

    }

    private fun getWeather() {
        _weatherState.value = Weather.Model("Привет")
    }
}