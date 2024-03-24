package ru.yasdev.planner.domain

import ru.yasdev.planner.data.PlannerRepository

class DeleteEventUseCase(
    private val repository: PlannerRepository
) {
    suspend fun execute(eventId: Int) {
        repository.deleteEvent(eventId)
    }

}