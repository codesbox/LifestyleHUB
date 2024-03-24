package ru.yasdev.sign_in.domain

import ru.yasdev.sign_in.data.SignInRepository
import ru.yasdev.sign_in.models.SignInState

internal class SignInUseCase(private val repository: SignInRepository) {

    suspend fun execute(login: String, password: String): SignInState {
        return repository.signIn(login = login, password = password)
    }

}