package ru.yasdev.planner

import ru.yasdev.details.data.AddEventFromDetailsRepository
import ru.yasdev.details.models.AddEventModel
import ru.yasdev.planner.models.NewEventModel

class AddEventFromDetailsRepositoryImpl(private val storage: PlannerStorage) :
    AddEventFromDetailsRepository {
    override suspend fun addEvent(model: AddEventModel) {
        storage.saveEvent(
            NewEventModel(
                title = model.title, date = model.date, note = null, link = model.link
            )
        )
    }
}