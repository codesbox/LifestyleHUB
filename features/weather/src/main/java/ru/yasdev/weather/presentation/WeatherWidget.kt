package ru.yasdev.weather.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.common.LocationState
import ru.yasdev.weather.models.WeatherState
import ru.yasdev.weather.models.WeatherEvent


@Composable
fun WeatherWidget(locationState: MutableState<LocationState>) {

    val viewModel = koinViewModel<WeatherViewModel>()

    val weather = viewModel.weatherState.collectAsState()

    when (locationState.value) {
        LocationState.Loading -> {}
        LocationState.NoPermissions -> {
            viewModel.onWeatherEvent(WeatherEvent.NoPermissions)
        }

        is LocationState.Model -> {
            viewModel.onWeatherEvent(WeatherEvent.GetWeather((locationState.value as LocationState.Model).location))
        }
    }
    Card(
        Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(start = 15.dp, top = 15.dp, end = 15.dp)
    ) {
        when (weather.value) {
            WeatherState.Loading -> {
                Box(
                    Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                }
            }

            WeatherState.NoPermissions -> {
                Box(
                    Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Разрешите доступ к местоположению")
                }
            }

            is WeatherState.Model -> {
                Row {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 15.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surface),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter((weather.value as WeatherState.Model).icon),
                                contentDescription = "null",
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text(
                                fontSize = MaterialTheme.typography.displayLarge.fontSize,
                                text = "${(weather.value as WeatherState.Model).temp}°"
                            )
                        }
                    }

                    Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(1f)) {
                        Text(
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            text = (weather.value as WeatherState.Model).city,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                        )
                        Text(
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            text = (weather.value as WeatherState.Model).title,
                            modifier = Modifier.padding(top = 5.dp, end = 15.dp)
                        )
                        Row(modifier = Modifier.padding(top = 5.dp, end = 15.dp)) {
                            Text(
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                text = "${(weather.value as WeatherState.Model).maxTemp}°",
                                modifier = Modifier.padding(end = 15.dp)
                            )
                            Text(
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                text = "${(weather.value as WeatherState.Model).minTemp}°"
                            )
                        }
                        Text(
                            text = "Ощущается как ${(weather.value as WeatherState.Model).feelLike}",
                            Modifier.padding(end = 15.dp)
                        )
                    }
                }
            }

            WeatherState.ErrorOnReceipt -> {
                Box(
                    Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Ошибка при загрузке")
                }
            }
        }
    }
}
