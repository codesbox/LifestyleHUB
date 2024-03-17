package ru.yasdev.weather.models

internal sealed interface WeatherEvent {
    data object NoPermissions : WeatherEvent
    data object GetWeather : WeatherEvent
}