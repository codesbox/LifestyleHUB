package ru.yasdev.lifestylehub.screens.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.lifestylehub.components.WeatherWidget

@Composable
fun HomeScreen(navController: NavController) {

    val vm = koinViewModel<HomeViewModel>()
    WeatherWidget(viewModel = vm)


}

