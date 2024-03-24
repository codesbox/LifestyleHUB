package ru.yasdev.planner

import ru.yasdev.planner.data.PlannerRepository
import ru.yasdev.planner.models.NewEventModel
import ru.yasdev.planner.models.PlannerState

class PlannerRepositoryImpl(
    private val storage: PlannerStorage
) : PlannerRepository {
    override suspend fun getEvents(): PlannerState {
        return storage.getEvents()
    }

    override suspend fun saveEvent(eventModel: NewEventModel) {
        return storage.saveEvent(eventModel)
    }

    override suspend fun deleteEvent(eventId: Int) {
        return storage.deleteEvent(eventId)
    }
}