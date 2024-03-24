package ru.yasdev.sign_up.navigation

sealed interface SignUpNavigator {
    data object PopBackStack : SignUpNavigator
    data object ToBeginningGraph : SignUpNavigator
}