package ru.yasdev.lifestylehub.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.common.LocationState
import ru.yasdev.lifestylehub.components.GetLocation
import ru.yasdev.recommendations_feed.presentation.RecommendationsFeedViewModel
import ru.yasdev.recommendations_feed.presentation.recommendationsFeed
import ru.yasdev.weather.presentation.weatherWidget
import ru.yasdev.recommendations_feed.models.FeedEvent
import ru.yasdev.recommendations_feed.models.RecommendationsFeedState
import ru.yasdev.weather.models.WeatherEvent
import ru.yasdev.weather.models.WeatherState
import ru.yasdev.weather.presentation.WeatherViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavController) {

    fun navigateToDetails(id: String) {
        navController.navigate("details/$id")
    }

    val locationState: MutableState<LocationState> = remember {
        mutableStateOf(LocationState.Loading)
    }

    GetLocation(locationState)

    val feedViewModel = koinViewModel<RecommendationsFeedViewModel>()
    val feedState = feedViewModel.recommendationsFeedState.collectAsState().value

    val isPagination = feedViewModel.isPagination.collectAsState().value
    if (isPagination) {
        feedViewModel.onEvent(FeedEvent.GetFeed)
    }

    val weatherViewModel = koinViewModel<WeatherViewModel>()
    val weatherState = weatherViewModel.weatherState.collectAsState().value

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        feedViewModel.onEvent(FeedEvent.RefreshFeed)
        weatherViewModel.onWeatherEvent(WeatherEvent.GetWeather)
        refreshing = false
    }

    LaunchedEffect(locationState.value) {
        when (locationState.value) {
            LocationState.Loading -> {}
            LocationState.NoPermissions -> {
                weatherViewModel.onWeatherEvent(WeatherEvent.NoPermissions)
                feedViewModel.onEvent(FeedEvent.NoPermissions)
            }

            is LocationState.Model -> {
                if(weatherState !is WeatherState.Model){
                    weatherViewModel.updateLocation((locationState.value as LocationState.Model).location)
                    weatherViewModel.onWeatherEvent(WeatherEvent.GetWeather)
                }
                if (feedState !is RecommendationsFeedState.Model) {
                    feedViewModel.updateLocation((locationState.value as LocationState.Model).location)
                    feedViewModel.onEvent(FeedEvent.RefreshFeed)
                }
            }
        }
    }

    val pullRefreshState = rememberPullRefreshState(refreshing, ::refresh)

    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn {
            weatherWidget(weatherState)
            recommendationsFeed(
                ::navigateToDetails, feedViewModel, feedState
            )
        }

        PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }

}

