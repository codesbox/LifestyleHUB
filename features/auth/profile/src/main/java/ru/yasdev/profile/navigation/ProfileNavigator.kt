package ru.yasdev.profile.navigation

sealed interface ProfileNavigator {
    data object ToBeginningGraph : ProfileNavigator
}