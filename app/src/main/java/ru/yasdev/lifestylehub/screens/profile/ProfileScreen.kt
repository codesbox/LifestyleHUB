package ru.yasdev.lifestylehub.screens.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.lifestylehub.navigation.Destinations
import ru.yasdev.lifestylehub.navigation.PROFILE_GRAPH_ROUTE
import ru.yasdev.lifestylehub.screens.profile.models.MainProfileState
import ru.yasdev.profile.navigation.ProfileNavigator
import ru.yasdev.profile.presentation.ProfileScreen
import ru.yasdev.sign_in.navigation.SignInNavigator
import ru.yasdev.sign_in.presentation.SignInScreen
import ru.yasdev.sign_up.SignUpScreen
import ru.yasdev.sign_up.navigation.SignUpNavigator

@Composable
fun MainProfileScreen(navController: NavHostController) {

    val viewModel = koinViewModel<MainProfileViewModel>()
    val state = viewModel.state.collectAsState().value

    fun signUpNavigation(navigator: SignUpNavigator){
        when(navigator){
            SignUpNavigator.PopBackStack -> {
                navController.popBackStack()
            }
            SignUpNavigator.ToBeginningGraph -> {
                navController.navigate(PROFILE_GRAPH_ROUTE)
            }
        }
    }

    fun signInNavigation(navigator: SignInNavigator){
        when(navigator){
            SignInNavigator.ToBeginningGraph -> {
                navController.navigate(PROFILE_GRAPH_ROUTE)
            }
            SignInNavigator.ToSignUpScreen -> {
                navController.navigate(Destinations.SignUpScreenRoute.route)
            }
        }
    }

    fun profileNavigation(navigator: ProfileNavigator){
        when(navigator){
            ProfileNavigator.ToBeginningGraph -> {
                navController.navigate(PROFILE_GRAPH_ROUTE)
            }
        }
    }

    when(state){
        MainProfileState.Loading -> {
            Text(text = "загрузка")
        }
        MainProfileState.Profile -> {
            navController.navigate(Destinations.ProfileScreenRoute.route)
        }
        MainProfileState.SignIn -> {
            navController.navigate(Destinations.SignInScreenRoute.route)
        }
    }

}