package ru.yasdev.lifestylehub.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.lifestylehub.navigation.Destinations
import ru.yasdev.lifestylehub.screens.profile.models.MainProfileState
import ru.yasdev.profile.navigation.ProfileNavigator
import ru.yasdev.profile.presentation.ProfileScreen
import ru.yasdev.sign_in.navigation.SignInNavigator
import ru.yasdev.sign_in.presentation.SignInScreen

@Composable
fun MainProfileScreen(navController: NavHostController) {

    val viewModel = koinViewModel<MainProfileViewModel>()
    viewModel.setState()
    val state = viewModel.state.collectAsState().value

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

    when (state) {
        MainProfileState.Loading -> {
            Box(
                Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
        }

        MainProfileState.Profile -> {
            ProfileScreen(::profileNavigation)
        }

        MainProfileState.SignIn -> {
            SignInScreen(::signInNavigation)
        }
    }
}