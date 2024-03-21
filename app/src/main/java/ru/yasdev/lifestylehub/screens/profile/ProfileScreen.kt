package ru.yasdev.lifestylehub.screens.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import ru.yasdev.sign_up.SignUpScreen

@Composable
fun ProfileScreen(navController: NavHostController) {
    Text(text = "profile screen")
    SignUpScreen()
}