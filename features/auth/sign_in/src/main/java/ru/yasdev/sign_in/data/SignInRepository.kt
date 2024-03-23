package ru.yasdev.sign_in.data

import ru.yasdev.sign_in.models.SignInState

interface SignInRepository {

    suspend fun signIn(login: String, password: String): SignInState

}