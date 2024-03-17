package ru.yasdev.weather.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.weather.models.Weather
import ru.yasdev.weather.models.WeatherEvent


@Composable
fun WeatherWidget() {

    val viewModel = koinViewModel<WeatherViewModel>()

    val weather = viewModel.weatherState.collectAsState()

    val context = LocalContext.current

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            viewModel.onWeatherEvent(WeatherEvent.GetWeather)
        } else {
            viewModel.onWeatherEvent(WeatherEvent.NoPermissions)
        }
    }


    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.onWeatherEvent(WeatherEvent.GetWeather)
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }
    Card(Modifier.fillMaxWidth().height(100.dp).padding(15.dp)) {
        when (weather.value) {
            Weather.Loading -> {
                Text(text = "Loading")
            }

            Weather.NoPermissions -> {
                Text(text = "ERROR")
            }

            is Weather.Model -> {
                Text(text = (weather.value as Weather.Model).text)
            }
        }
    }
}
