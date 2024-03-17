package ru.yasdev.lifestylehub.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.weather.presentation.WeatherWidget

@Composable
fun HomeScreen(navController: NavController) {

    val vm = koinViewModel<HomeViewModel>()
    WeatherWidget()




}

