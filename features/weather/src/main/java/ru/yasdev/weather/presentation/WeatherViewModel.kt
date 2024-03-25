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

class WeatherViewModel(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState = _weatherState.asStateFlow()
    private val _location = MutableStateFlow<Location?>(null)

    fun updateLocation(location: Location?) {
        _location.value = location
    }

    fun onWeatherEvent(weatherEvent: WeatherEvent) {
        when (weatherEvent) {
            WeatherEvent.NoPermissions -> {
                _weatherState.value = WeatherState.NoPermissions
            }

            WeatherEvent.GetWeather -> {
                getWeather(_location.value)
            }
        }
    }

    private fun getWeather(location: Location?) {
        viewModelScope.launch {
            _weatherState.value = WeatherState.Loading
            _weatherState.value = getWeatherUseCase.execute(location)
        }
    }
}