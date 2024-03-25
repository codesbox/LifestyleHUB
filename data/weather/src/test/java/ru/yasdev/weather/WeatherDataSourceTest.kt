package ru.yasdev.weather

import android.location.Location
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import ru.yasdev.weather.models.WeatherState


class WeatherDataSourceTest {

    @Test
    fun `test getWeather() with null location should return NoPermissions`() {
        val weatherDataSource = MockWeatherDataSource()
        val weatherState = runBlocking {
            weatherDataSource.getWeather(null)
        }
        assertTrue(weatherState is WeatherState.NoPermissions)
    }

    @Test
    fun `test getWeather() with valid location should return Model`() {
        val location = mock(Location::class.java)
        `when`(location.latitude).thenReturn(12.345)
        `when`(location.longitude).thenReturn(67.890)

        val weatherDataSource = MockWeatherDataSource()
        val weatherState = runBlocking {
            weatherDataSource.getWeather(location)
        }
        assertTrue(weatherState is WeatherState.Model)
    }
}

class MockWeatherDataSource : WeatherDataSource {
    override suspend fun getWeather(location: Location?): WeatherState {
        return if (location != null) {
            WeatherState.Model(
                temp = "25°C",
                icon = "sunny",
                minTemp = "20°C",
                maxTemp = "30°C",
                feelLike = "26°C",
                title = "Sunny Day",
                city = "Test City"
            )
        } else {
            WeatherState.NoPermissions
        }
    }
}