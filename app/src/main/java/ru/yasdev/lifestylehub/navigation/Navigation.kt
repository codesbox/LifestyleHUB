package ru.yasdev.lifestylehub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.coroutines.flow.StateFlow
import ru.yasdev.details.models.BaseDetails
import ru.yasdev.details.presentation.DetailsScreen
import ru.yasdev.lifestylehub.activity.SharedViewModel
import ru.yasdev.lifestylehub.screens.home.HomeScreen
import ru.yasdev.lifestylehub.screens.myLeisure.LeisureScreen
import ru.yasdev.lifestylehub.screens.profile.ProfileScreen


const val BOTTOM_NAV_GRAPH_ROUTE = "btn_nav_graph"
const val HOME_GRAPH_ROUTE = "home_nav_graph"
const val PROFILE_GRAPH_ROUTE = "profile_nav_graph"
const val LEISURE_GRAPH_ROUTE = "leisure_nav_graph"

sealed class Destinations(
    val route: String
) {
    data object HomeScreenRoute : Destinations(route = "home")
    data object DetailsScreenRoute : Destinations(route = "details")
    data object LeisureScreenRoute : Destinations(route = "leisure")
    data object ProfileScreenRoute : Destinations(route = "profile")

}

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    baseDetails: StateFlow<BaseDetails?>,
    setBaseDetails: (details: BaseDetails) -> Unit
) {
    navigation(
        startDestination = Destinations.HomeScreenRoute.route, route = HOME_GRAPH_ROUTE
    ) {
        composable(route = Destinations.HomeScreenRoute.route) { HomeScreen(navController = navController, setBaseDetails) }
        composable(route = Destinations.DetailsScreenRoute.route) {
            DetailsScreen(baseDetails = baseDetails)

        }
    }
}

fun NavGraphBuilder.myLeisureNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Destinations.LeisureScreenRoute.route, route = LEISURE_GRAPH_ROUTE
    ) {
        composable(route = Destinations.LeisureScreenRoute.route) { LeisureScreen(navController) }
    }
}

fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Destinations.ProfileScreenRoute.route, route = PROFILE_GRAPH_ROUTE
    ) {
        composable(route = Destinations.ProfileScreenRoute.route) { ProfileScreen(navController) }
    }
}

fun NavController.navigateBetweenGraphs(graphName: String) {
    navigate(graphName) {
        popUpTo(this@navigateBetweenGraphs.graph.startDestinationId) {
            inclusive = true
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun BottomBarNavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    NavHost(
        navController = navController,
        startDestination = HOME_GRAPH_ROUTE,
        route = BOTTOM_NAV_GRAPH_ROUTE
    ) {
        homeNavGraph(navController, sharedViewModel.baseDetails, sharedViewModel::setBaseDetails)
        myLeisureNavGraph(navController)
        profileNavGraph(navController)
    }
}