package ru.yasdev.sign_in.models

interface SignInState {
    data object SignIn : SignInState
    data object Loading : SignInState
    data object UserNotFound : SignInState
    data object Success : SignInState
}