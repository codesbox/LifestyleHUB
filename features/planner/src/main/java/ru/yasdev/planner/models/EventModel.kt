package ru.yasdev.planner.models

data class EventModel(
    val title: String, val date: String, val note: String?, val link: String?, val eventId: Int
)
