package ru.yasdev.planner.domain

import ru.yasdev.planner.data.PlannerRepository
import ru.yasdev.planner.models.NewEventModel

class AddEventUseCase(
    private val repository: PlannerRepository
) {

    suspend fun execute(eventModel: NewEventModel) {
        repository.saveEvent(eventModel)
    }

}