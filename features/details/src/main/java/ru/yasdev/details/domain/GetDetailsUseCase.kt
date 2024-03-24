package ru.yasdev.details.domain

import ru.yasdev.details.data.DetailsRepository
import ru.yasdev.details.models.DetailsState

internal class GetDetailsUseCase(private val detailsRepository: DetailsRepository) {

    suspend fun execute(id: String): DetailsState {
        return detailsRepository.getDetails(id)
    }

}