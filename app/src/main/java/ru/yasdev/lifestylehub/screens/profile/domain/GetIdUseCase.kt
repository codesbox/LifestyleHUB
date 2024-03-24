package ru.yasdev.lifestylehub.screens.profile.domain

import ru.yasdev.common.GetUserIdRepository

class GetIdUseCase(
    private val getUserIdRepository: GetUserIdRepository
) {
    suspend fun execute(): String? {
        return getUserIdRepository.getId()
    }
}