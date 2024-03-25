package ru.yasdev.weather.models


sealed interface WeatherEvent {
    data object NoPermissions : WeatherEvent
    data object GetWeather : WeatherEvent
}