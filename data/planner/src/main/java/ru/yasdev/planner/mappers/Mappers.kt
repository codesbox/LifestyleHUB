package ru.yasdev.planner.mappers

import ru.yasdev.planner.EventEntity
import ru.yasdev.planner.models.EventModel

fun EventEntity.toEventModel(): EventModel{
    return EventModel(
        title = title,
        date = date,
        eventId = eventId,
        link = link,
        note = note
    )
}