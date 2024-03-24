package ru.yasdev.planner.mappers

import ru.yasdev.planner.EventEntity
import ru.yasdev.planner.models.EventModel
import ru.yasdev.planner.models.NewEventModel

fun EventEntity.toEventModel(): EventModel {
    return EventModel(
        title = title, date = date, eventId = eventId, link = link, note = note
    )
}

fun NewEventModel.toEventEntity(userId: String): EventEntity {
    return EventEntity(
        title = title, note = note, link = link, date = date, userId = userId
    )
}