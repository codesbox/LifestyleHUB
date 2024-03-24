package ru.yasdev.planner.domain

import ru.yasdev.planner.data.PlannerRepository
import ru.yasdev.planner.models.PlannerState

class GetEventsUseCase(
    private val repository: PlannerRepository
) {

    suspend fun execute(): PlannerState {
        return repository.getEvents()
    }
}