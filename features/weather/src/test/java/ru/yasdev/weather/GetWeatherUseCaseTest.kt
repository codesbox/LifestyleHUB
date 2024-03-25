package ru.yasdev.weather

import android.location.Location
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import ru.yasdev.weather.data.WeatherRepository
import ru.yasdev.weather.domain.GetWeatherUseCase
import ru.yasdev.weather.models.WeatherState


class GetWeatherUseCaseTest {

    @Test
    fun `test execute() with null location should return NoPermissions`() {
        val weatherRepository = mock(WeatherRepository::class.java)
        val getWeatherUseCase = GetWeatherUseCase(weatherRepository)

        `when`(runBlocking { weatherRepository.getWeather(null) }).thenReturn(
            WeatherState.NoPermissions
        )
        val weatherState = runBlocking {
            getWeatherUseCase.execute(null)
        }
        assertTrue(weatherState is WeatherState.NoPermissions)
    }

    @Test
    fun `test execute() with valid location should return Model`() {
        val weatherRepository = mock(WeatherRepository::class.java)
        val getWeatherUseCase = GetWeatherUseCase(weatherRepository)
        val location = mock(Location::class.java)
        `when`(location.latitude).thenReturn(12.345)
        `when`(location.longitude).thenReturn(67.890)

        `when`(runBlocking { weatherRepository.getWeather(location) }).thenReturn(
            WeatherState.Model(
                temp = "25°C",
                icon = "sunny",
                minTemp = "20°C",
                maxTemp = "30°C",
                feelLike = "26°C",
                title = "Sunny Day",
                city = "Test City"
            )
        )

        val weatherState = runBlocking {
            getWeatherUseCase.execute(location)
        }
        assertTrue(weatherState is WeatherState.Model)
    }

}