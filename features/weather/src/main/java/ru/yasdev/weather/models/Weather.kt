package ru.yasdev.weather.models

sealed interface Weather {
    data object Loading : Weather
    data object NoPermissions : Weather
    data class Model(
        val temp: String,
        val icon: String,
        val minTemp: String,
        val maxTemp: String,
        val feelLike: String,
        val title: String,
        val city: String
    ) : Weather
    data object ErrorOnReceipt: Weather
}