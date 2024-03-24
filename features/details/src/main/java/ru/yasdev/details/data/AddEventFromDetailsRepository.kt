package ru.yasdev.details.data

import ru.yasdev.details.models.AddEventModel

interface AddEventFromDetailsRepository {

    suspend fun addEvent(model: AddEventModel)
}