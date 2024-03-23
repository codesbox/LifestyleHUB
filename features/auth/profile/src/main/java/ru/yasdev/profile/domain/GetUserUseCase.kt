package ru.yasdev.profile.domain

import ru.yasdev.profile.data.ProfileRepository
import ru.yasdev.profile.models.ProfileState

class GetUserUseCase(
    private val profileRepository: ProfileRepository
) {

    suspend fun execute(): ProfileState{
        return profileRepository.getUser()
    }

}