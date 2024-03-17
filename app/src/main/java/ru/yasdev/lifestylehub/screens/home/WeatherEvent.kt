package ru.yasdev.lifestylehub.screens.home

sealed interface WeatherEvent {
    data object NoPermissions : WeatherEvent
    data object GetWeather : WeatherEvent
}