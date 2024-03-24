package ru.yasdev.weather.mappers

import ru.yasdev.weather.models.WeatherState
import ru.yasdev.weather.models.WeatherDTO
import kotlin.math.roundToInt

fun WeatherDTO.toWeatherModel(): WeatherState.Model {
    return WeatherState.Model(
        temp = main.temp.roundToInt().toString(),
        minTemp = main.minTemp.roundToInt().toString(),
        maxTemp = main.maxTemp.roundToInt().toString(),
        feelLike = main.feelsLike.roundToInt().toString(),
        title = weather[0].title,
        city = city,
        icon = "https://openweathermap.org/img/wn/${weather[0].icon}@2x.png"
    )
}