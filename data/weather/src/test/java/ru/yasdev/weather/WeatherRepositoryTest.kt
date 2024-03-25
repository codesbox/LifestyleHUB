package ru.yasdev.weather

import android.location.Location
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import ru.yasdev.weather.data.WeatherRepository
import ru.yasdev.weather.models.WeatherState

class WeatherRepositoryTest {

    @Test
    fun `test getWeather() with null location should return NoPermissions`() {
        val weatherRepository = MockWeatherRepository()
        val weatherState = runBlocking {
            weatherRepository.getWeather(null)
        }
        Assert.assertTrue(weatherState is WeatherState.NoPermissions)
    }

    @Test
    fun `test getWeather() with valid location should return Model`() {
        val location = Mockito.mock(Location::class.java)
        Mockito.`when`(location.latitude).thenReturn(12.345)
        Mockito.`when`(location.longitude).thenReturn(67.890)
        val weatherRepository = MockWeatherRepository()
        val weatherState = runBlocking {
            weatherRepository.getWeather(location)
        }
        Assert.assertTrue(weatherState is WeatherState.Model)
    }

}

class MockWeatherRepository : WeatherRepository {
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