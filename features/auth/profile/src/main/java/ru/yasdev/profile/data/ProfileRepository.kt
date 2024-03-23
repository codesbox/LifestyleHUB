package ru.yasdev.profile.data

import ru.yasdev.profile.models.ProfileState

interface ProfileRepository {

    suspend fun getUser(): ProfileState

    suspend fun logOut()
}