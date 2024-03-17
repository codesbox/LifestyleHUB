package ru.yasdev.domain.weather

sealed interface Weather {
    data object Loading : Weather
    data object NoPermissions : Weather
    data class Model(val text: String) : Weather
}