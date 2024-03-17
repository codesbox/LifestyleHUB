package ru.yasdev.lifestylehub.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.yasdev.domain.weather.Weather

class HomeViewModel : ViewModel() {

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

    private fun getWeather() {}


}