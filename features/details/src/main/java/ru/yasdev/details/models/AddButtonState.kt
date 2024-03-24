package ru.yasdev.details.models

sealed interface AddButtonState {
    data object Ok : AddButtonState
    data object NoAuth : AddButtonState
}