package ru.yasdev.details.models

data class BaseDetails(
    val title: String,
    val address: String,
    val photoList: List<String>,
    val categories: List<String>,
    val id: String
)
