package ru.yasdev.lifestylehub.screens.profile.models

sealed interface MainProfileState {
    data object Loading : MainProfileState
    data object SignIn : MainProfileState
    data object Profile : MainProfileState
}