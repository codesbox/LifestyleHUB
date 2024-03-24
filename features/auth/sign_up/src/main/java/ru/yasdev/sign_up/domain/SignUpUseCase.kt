package ru.yasdev.sign_up.domain

import ru.yasdev.sign_up.data.SignUpRepository
import ru.yasdev.sign_up.models.SignUpState

internal class SignUpUseCase(
    private val signUpRepository: SignUpRepository
) {


    suspend fun execute(password: String): SignUpState {
        return signUpRepository.signUp(password)
    }
}