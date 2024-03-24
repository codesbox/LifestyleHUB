package ru.yasdev.sign_in.navigation

sealed interface SignInNavigator {
    data object ToSignUpScreen : SignInNavigator
    data object ToBeginningGraph : SignInNavigator
}