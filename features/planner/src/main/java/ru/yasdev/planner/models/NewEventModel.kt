package ru.yasdev.planner.models

data class NewEventModel(
    val title: String, val date: String, val note: String?, val link: String?
)
