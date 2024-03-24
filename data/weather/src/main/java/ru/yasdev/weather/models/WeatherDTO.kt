package ru.yasdev.weather.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDTO(

    val main: Main, val weather: List<WeatherInDTO>, @SerialName("name") val city: String

)

@Serializable
data class Main(
    val temp: Double,
    @SerialName("temp_min") val minTemp: Double,
    @SerialName("temp_max") val maxTemp: Double,
    @SerialName("feels_like") val feelsLike: Double
)

@Serializable
data class WeatherInDTO(
    val icon: String, @SerialName("main") val title: String
)
