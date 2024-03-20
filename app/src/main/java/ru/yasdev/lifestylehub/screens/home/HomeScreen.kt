package ru.yasdev.lifestylehub.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.common.LocationState
import ru.yasdev.lifestylehub.components.GetLocation
import ru.yasdev.recommendations_feed.presentation.RecommendationsFeed
import ru.yasdev.weather.presentation.WeatherWidget

@Composable
fun HomeScreen(navController: NavController) {

    val vm = koinViewModel<HomeViewModel>()

    val locationState: MutableState<LocationState> = remember {
        mutableStateOf(LocationState.Loading)
    }
    GetLocation(locationState)
    WeatherWidget(locationState)
    RecommendationsFeed(locationState)
}

