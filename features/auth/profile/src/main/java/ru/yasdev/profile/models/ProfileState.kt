package ru.yasdev.profile.models

sealed interface ProfileState {
    data class Profile(val firstName: String, val lastName: String, val image: String, val id: String) :
        ProfileState

    data object Loading : ProfileState
    data object ErrorOnReceipt : ProfileState
}