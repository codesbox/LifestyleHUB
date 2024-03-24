package ru.yasdev.weather.models

sealed interface WeatherState {
    data object Loading : WeatherState
    data object NoPermissions : WeatherState
    data class Model(
        val temp: String,
        val icon: String,
        val minTemp: String,
        val maxTemp: String,
        val feelLike: String,
        val title: String,
        val city: String
    ) : WeatherState

    data object ErrorOnReceipt : WeatherState
}