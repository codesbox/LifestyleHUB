package ru.yasdev.details.models

sealed interface DetailsState {

    data object Loading : DetailsState
    data class Details(val photo: String?, val contacts: String) : DetailsState
    data object ErrorOnReceipt : DetailsState

}