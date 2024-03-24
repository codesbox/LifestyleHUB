package ru.yasdev.details.domain

import ru.yasdev.details.data.AddEventFromDetailsRepository
import ru.yasdev.details.models.AddEventModel

internal class AddEventUseCase(
    private val repository: AddEventFromDetailsRepository
) {
    suspend fun execute(model: AddEventModel) {
        repository.addEvent(model)
    }

}