package ru.yasdev.recommendations_feed.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.common.LocationState

@Composable
fun RecommendationsFeed(locationState: MutableState<LocationState>){

    val viewModel = koinViewModel<RecommendationsFeedViewModel>()


    when(locationState.value){
        LocationState.Loading -> {}
        LocationState.NoPermissions -> {
        //viewModel.onWeatherEvent(WeatherEvent.NoPermissions)
    }
        is LocationState.Model -> {
        viewModel.test((locationState.value as LocationState.Model).location)
    }
    }

}