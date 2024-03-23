package ru.yasdev.profile.domain

import ru.yasdev.profile.data.ProfileRepository

class LogOutUseCase(
    private val profileRepository: ProfileRepository
) {

    suspend fun execute(){
        profileRepository.logOut()
    }

}