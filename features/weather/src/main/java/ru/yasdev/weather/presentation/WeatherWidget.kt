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
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ru.yasdev.common.shimmerEffect
import ru.yasdev.weather.R
import ru.yasdev.weather.models.WeatherState


fun LazyListScope.weatherWidget(
    state: WeatherState
) {

    item {
        Card(
            Modifier
                .fillMaxWidth()
                .height(170.dp)
                .padding(start = 15.dp, top = 15.dp, end = 15.dp)
        ) {
            when (state) {
                WeatherState.Loading -> {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .shimmerEffect(), contentAlignment = Alignment.Center
                    ) {

                    }
                }

                WeatherState.NoPermissions -> {
                    Box(
                        Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(id = R.string.error_location))
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
                                    painter = rememberAsyncImagePainter(state.icon),
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
                                    text = "${state.temp}°"
                                )
                            }
                        }

                        Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(1f)) {
                            Text(
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                text = state.city,
                                modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                            )
                            Text(
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                text = state.title,
                                modifier = Modifier.padding(top = 5.dp, end = 15.dp)
                            )
                            Row(modifier = Modifier.padding(top = 5.dp, end = 15.dp)) {
                                Text(
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    text = "${state.maxTemp}°",
                                    modifier = Modifier.padding(end = 15.dp)
                                )
                                Text(
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    text = "${state.minTemp}°"
                                )
                            }
                            Text(
                                text = "${stringResource(id = R.string.feel_like)} ${state.feelLike}",
                                Modifier.padding(end = 15.dp)
                            )
                        }
                    }
                }

                WeatherState.ErrorOnReceipt -> {
                    Box(
                        Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(id = R.string.error))
                    }
                }
            }
        }
    }
}
