package ru.yasdev.weather.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.fadeIn
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.android.gms.location.LocationServices
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
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    viewModel.onWeatherEvent(WeatherEvent.GetWeather(location))
                }

        } else {
            viewModel.onWeatherEvent(WeatherEvent.NoPermissions)
        }
    }


    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    viewModel.onWeatherEvent(WeatherEvent.GetWeather(location))
                }
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }
    Card(
        Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(15.dp)) {
        when (weather.value) {
            Weather.Loading -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                }
            }

            Weather.NoPermissions -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "Разрешите доступ к местоположению")
                }
            }

            is Weather.Model -> {
                Row {
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 15.dp), contentAlignment = Alignment.Center){
                        Box(modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface), contentAlignment = Alignment.Center){
                            Image(
                                painter = rememberAsyncImagePainter((weather.value as Weather.Model).icon),
                                contentDescription = "null",
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    }
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.Center) {
                        Column {
                            Text(fontSize = MaterialTheme.typography.displayLarge.fontSize, text = "${(weather.value as Weather.Model).temp}°")
                        }
                    }
                    
                    Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(1f)) {
                        Text(fontSize = MaterialTheme.typography.titleMedium.fontSize,text = (weather.value as Weather.Model).city, modifier = Modifier.padding(top = 15.dp, end = 15.dp))
                        Text(fontSize = MaterialTheme.typography.titleMedium.fontSize,text = (weather.value as Weather.Model).title, modifier = Modifier.padding(top = 5.dp, end = 15.dp))
                        Row(modifier = Modifier.padding(top = 5.dp, end = 15.dp)) {
                            Text(fontSize = MaterialTheme.typography.titleLarge.fontSize, text = "${(weather.value as Weather.Model).maxTemp}°", modifier = Modifier.padding(end = 15.dp))
                            Text(fontSize = MaterialTheme.typography.titleLarge.fontSize, text = "${(weather.value as Weather.Model).minTemp}°")
                        }
                        Text(text = "Ощущается как ${(weather.value as Weather.Model).feelLike}", Modifier.padding(end = 15.dp))
                    }
                    
                }
            }
            Weather.ErrorOnReceipt -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "Ошибка при загрузке")
                }
            }
        }
    }
}
