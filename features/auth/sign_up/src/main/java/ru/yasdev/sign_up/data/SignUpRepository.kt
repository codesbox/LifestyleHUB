package ru.yasdev.sign_up.data

import ru.yasdev.sign_up.models.SignUpState

interface SignUpRepository {
    suspend fun signUp(password: String): SignUpState
}