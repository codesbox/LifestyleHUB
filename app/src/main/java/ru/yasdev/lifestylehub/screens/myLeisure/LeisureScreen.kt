package ru.yasdev.lifestylehub.screens.myLeisure

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import ru.yasdev.planner.presentation.PlannerScreen

@Composable
fun LeisureScreen(navController: NavHostController) {

    fun navigateToDetails(id: String) {
        navController.navigate("details/$id")
    }

    PlannerScreen(::navigateToDetails)
}