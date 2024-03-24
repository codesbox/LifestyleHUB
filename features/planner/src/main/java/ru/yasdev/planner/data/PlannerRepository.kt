package ru.yasdev.planner.data

import ru.yasdev.planner.models.NewEventModel
import ru.yasdev.planner.models.PlannerState

interface PlannerRepository {

    suspend fun getEvents(): PlannerState
    suspend fun saveEvent(eventModel: NewEventModel)
    suspend fun deleteEvent(eventId: Int)
}