package ru.yasdev.weather.models

import android.location.Location

internal sealed interface WeatherEvent {
    data object NoPermissions : WeatherEvent
    data class GetWeather(val location: Location?) : WeatherEvent
}