package ru.yasdev.lifestylehub.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.common.LocationState
import ru.yasdev.details.models.BaseDetails
import ru.yasdev.lifestylehub.components.GetLocation
import ru.yasdev.lifestylehub.navigation.Destinations
import ru.yasdev.recommendations_feed.models.RecommendationModel
import ru.yasdev.recommendations_feed.presentation.RecommendationsFeed
import ru.yasdev.weather.presentation.WeatherWidget

@Composable
fun HomeScreen(navController: NavController, setBaseDetails: (details: BaseDetails) -> Unit) {

    fun navigateToDetails(recommendationModel: RecommendationModel){
        setBaseDetails(BaseDetails(title = recommendationModel.title,
            address = recommendationModel.address, photoList = recommendationModel.photoList,
            categories = recommendationModel.categories, id = recommendationModel.id))
        navController.navigate(Destinations.DetailsScreenRoute.route)
    }

    val vm = koinViewModel<HomeViewModel>()

    val locationState: MutableState<LocationState> = remember {
        mutableStateOf(LocationState.Loading)
    }
    GetLocation(locationState)
    Column {
        WeatherWidget(locationState)
        RecommendationsFeed(locationState, ::navigateToDetails)
    }

}

