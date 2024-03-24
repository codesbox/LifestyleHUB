package ru.yasdev.weather.presentation

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yasdev.weather.domain.GetWeatherUseCase
import ru.yasdev.weather.models.WeatherState
import ru.yasdev.weather.models.WeatherEvent

internal class WeatherViewModel(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState = _weatherState.asStateFlow()

    fun onWeatherEvent(weatherEvent: WeatherEvent) {
        when (weatherEvent) {
            WeatherEvent.NoPermissions -> {
                _weatherState.value = WeatherState.NoPermissions
            }

            is WeatherEvent.GetWeather -> {
                getWeather(weatherEvent.location)
            }
        }
    }

    private fun getWeather(location: Location?) {
        viewModelScope.launch {
            getWeatherUseCase.execute(location).collect { weather ->
                _weatherState.value = weather
            }
        }
    }
}