package ru.yasdev.profile.domain

import ru.yasdev.profile.data.ProfileRepository

internal class LogOutUseCase(
    private val profileRepository: ProfileRepository
) {
    suspend fun execute() {
        profileRepository.logOut()
    }

}