package ru.yasdev.details.domain

import ru.yasdev.common.GetUserIdRepository

internal class GetUserIdUseCase(
    private val repository: GetUserIdRepository
) {

    suspend fun execute(): String? {
        return repository.getId()
    }
}