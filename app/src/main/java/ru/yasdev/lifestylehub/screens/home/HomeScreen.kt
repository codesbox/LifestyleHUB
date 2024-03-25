package ru.yasdev.lifestylehub.screens.home

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.common.LocationState
import ru.yasdev.lifestylehub.components.GetLocation
import ru.yasdev.recommendations_feed.presentation.RecommendationsFeedViewModel
import ru.yasdev.recommendations_feed.presentation.recommendationsFeed
import ru.yasdev.weather.presentation.weatherWidget
import ru.yasdev.recommendations_feed.models.FeedEvent

@Composable
fun HomeScreen(navController: NavController) {

    fun navigateToDetails(id: String) {
        navController.navigate("details/$id")
    }

    val locationState: MutableState<LocationState> = remember {
        mutableStateOf(LocationState.Loading)
    }
    GetLocation(locationState)


    val viewModel = koinViewModel<RecommendationsFeedViewModel>()
    val state = viewModel.recommendationsFeedState.collectAsState().value
    val isPagination = viewModel.isPagination.collectAsState().value
    if (isPagination) {
        viewModel.onEvent(FeedEvent.GetFeed)
    }

    LazyColumn {
        weatherWidget(locationState)
        recommendationsFeed(
            locationState, ::navigateToDetails, viewModel, state
        )
    }

}

