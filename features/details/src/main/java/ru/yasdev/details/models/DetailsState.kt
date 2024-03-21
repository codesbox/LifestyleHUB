package ru.yasdev.details.models

sealed interface DetailsState {

    data object Loading : DetailsState
    data class Details(
        val title: String,
        val address: String,
        val photoList: List<String>,
        val categories: List<String>,
        val id: String
    ) : DetailsState

    data object ErrorOnReceipt : DetailsState


}