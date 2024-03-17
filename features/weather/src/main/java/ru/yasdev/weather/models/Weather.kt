package ru.yasdev.weather.models

sealed interface Weather {
    data object Loading : Weather
    data object NoPermissions : Weather
    data class Model(val text: String) : Weather
}