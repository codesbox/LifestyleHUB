package ru.yasdev.details.domain

import ru.yasdev.common.GetUserIdRepository

class GetUserIdUseCase(
    private val repository: GetUserIdRepository
) {

    suspend fun execute(): String?{
        return repository.getId()
    }
}