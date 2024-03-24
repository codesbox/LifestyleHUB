package ru.yasdev.auth.sign_up

import ru.yasdev.sign_up.data.SignUpRepository
import ru.yasdev.sign_up.models.SignUpState

class SignUpRepositoryImpl(
    private val signUpDataSource: SignUpDataSource
) : SignUpRepository {
    override suspend fun signUp(password: String): SignUpState {
        return signUpDataSource.signUp(password)
    }
}