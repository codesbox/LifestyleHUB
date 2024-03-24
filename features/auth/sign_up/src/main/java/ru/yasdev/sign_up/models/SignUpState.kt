package ru.yasdev.sign_up.models

sealed interface SignUpState {
    data object SignUp : SignUpState
    data object Loading : SignUpState
    data object ErrorOnReceipt : SignUpState
    data object Success : SignUpState
}