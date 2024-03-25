package ru.yasdev.lifestylehub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.yasdev.details.presentation.DetailsScreen
import ru.yasdev.lifestylehub.screens.home.HomeScreen
import ru.yasdev.lifestylehub.screens.myLeisure.LeisureScreen
import ru.yasdev.lifestylehub.screens.profile.MainProfileScreen
import ru.yasdev.profile.navigation.ProfileNavigator
import ru.yasdev.profile.presentation.ProfileScreen
import ru.yasdev.sign_in.navigation.SignInNavigator
import ru.yasdev.sign_in.presentation.SignInScreen
import ru.yasdev.sign_up.SignUpScreen
import ru.yasdev.sign_up.navigation.SignUpNavigator


const val BOTTOM_NAV_GRAPH_ROUTE = "btn_nav_graph"
const val HOME_GRAPH_ROUTE = "home_nav_graph"
const val PROFILE_GRAPH_ROUTE = "profile_nav_graph"
const val LEISURE_GRAPH_ROUTE = "leisure_nav_graph"

sealed class Destinations(
    val route: String
) {
    data object HomeScreenRoute : Destinations(route = "home")
    data object DetailsScreenRoute : Destinations(route = "details/{id}")
    data object LeisureScreenRoute : Destinations(route = "leisure")
    data object MainProfileScreenRoute : Destinations(route = "mainProfile")
    data object SignInScreenRoute : Destinations(route = "signIn")
    data object SignUpScreenRoute : Destinations(route = "signUp")
    data object ProfileScreenRoute : Destinations(route = "profile")

}

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Destinations.HomeScreenRoute.route, route = HOME_GRAPH_ROUTE
    ) {

        fun popBackStack() {
            navController.popBackStack()
        }

        composable(route = Destinations.HomeScreenRoute.route) { HomeScreen(navController = navController) }
        composable(route = Destinations.DetailsScreenRoute.route) { backStackEntry ->
            DetailsScreen(backStackEntry.arguments?.getString("id"), ::popBackStack)
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
        startDestination = Destinations.MainProfileScreenRoute.route, route = PROFILE_GRAPH_ROUTE
    ) {

        fun signUpNavigation(navigator: SignUpNavigator) {
            when (navigator) {
                SignUpNavigator.PopBackStack -> {
                    navController.popBackStack()
                }

                SignUpNavigator.ToBeginningGraph -> {
                    navController.navigate(Destinations.MainProfileScreenRoute.route) {
                        popUpTo(Destinations.MainProfileScreenRoute.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }

        fun signInNavigation(navigator: SignInNavigator) {
            when (navigator) {
                SignInNavigator.ToBeginningGraph -> {
                    navController.navigate(Destinations.MainProfileScreenRoute.route) {
                        popUpTo(Destinations.MainProfileScreenRoute.route) {
                            inclusive = true
                        }
                    }
                }

                SignInNavigator.ToSignUpScreen -> {
                    navController.navigate(Destinations.SignUpScreenRoute.route)
                }
            }
        }

        fun profileNavigation(navigator: ProfileNavigator) {
            when (navigator) {
                ProfileNavigator.ToBeginningGraph -> {
                    navController.navigate(Destinations.MainProfileScreenRoute.route) {
                        popUpTo(Destinations.MainProfileScreenRoute.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }

        composable(route = Destinations.MainProfileScreenRoute.route) {
            MainProfileScreen(
                navController
            )
        }
        composable(route = Destinations.SignInScreenRoute.route) { SignInScreen(::signInNavigation) }
        composable(route = Destinations.SignUpScreenRoute.route) { SignUpScreen(::signUpNavigation) }
        composable(route = Destinations.ProfileScreenRoute.route) { ProfileScreen(::profileNavigation) }
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
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HOME_GRAPH_ROUTE,
        route = BOTTOM_NAV_GRAPH_ROUTE
    ) {
        homeNavGraph(navController)
        myLeisureNavGraph(navController)
        profileNavGraph(navController)
    }
}