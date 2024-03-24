package ru.yasdev.auth.sign_in

import ru.yasdev.sign_in.data.SignInRepository
import ru.yasdev.sign_in.models.SignInState

class SignInRepositoryImpl(private val dataSource: SignInDataSource) : SignInRepository {
    override suspend fun signIn(login: String, password: String): SignInState {
        return dataSource.signIn(login = login, password = password)
    }

}