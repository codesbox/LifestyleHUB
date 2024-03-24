package ru.yasdev.lifestylehub.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import ru.yasdev.common.LocationState
import ru.yasdev.lifestylehub.components.GetLocation
import ru.yasdev.recommendations_feed.presentation.RecommendationsFeed
import ru.yasdev.weather.presentation.WeatherWidget

@Composable
fun HomeScreen(navController: NavController) {

    fun navigateToDetails(id: String) {
        navController.navigate("details/$id")
    }

    val locationState: MutableState<LocationState> = remember {
        mutableStateOf(LocationState.Loading)
    }
    GetLocation(locationState)
    Column {
        WeatherWidget(locationState)
        RecommendationsFeed(locationState, ::navigateToDetails)
    }

}

