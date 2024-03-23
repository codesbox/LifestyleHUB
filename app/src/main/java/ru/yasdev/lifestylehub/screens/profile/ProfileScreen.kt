package ru.yasdev.lifestylehub.screens.profile

import android.util.Log
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
    Log.d("QWE", "MPS")

    val viewModel = koinViewModel<MainProfileViewModel>()
    viewModel.setState()
    val state = viewModel.state.collectAsState().value


    fun signInNavigation(navigator: SignInNavigator){
        when(navigator){
            SignInNavigator.ToBeginningGraph -> {
                navController.navigate(Destinations.MainProfileScreenRoute.route){
                    popUpTo(Destinations.MainProfileScreenRoute.route){
                        inclusive = true
                    }
                }
            }
            SignInNavigator.ToSignUpScreen -> {
                navController.navigate(Destinations.SignUpScreenRoute.route)
            }
        }
    }

    fun profileNavigation(navigator: ProfileNavigator){
        when(navigator){
            ProfileNavigator.ToBeginningGraph -> {
                navController.navigate(Destinations.MainProfileScreenRoute.route){
                    popUpTo(Destinations.MainProfileScreenRoute.route){
                        inclusive = true
                    }
                }
            }
        }
    }

    when(state){
        MainProfileState.Loading -> {
            Text(text = "загрузка")
        }
        MainProfileState.Profile -> {
            ProfileScreen(::profileNavigation)
        }
        MainProfileState.SignIn -> {
            SignInScreen(::signInNavigation)
        }
    }

}