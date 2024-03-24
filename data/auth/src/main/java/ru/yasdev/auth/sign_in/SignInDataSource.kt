package ru.yasdev.auth.sign_in

import android.content.Context
import androidx.room.Room
import ru.yasdev.auth.Constants
import ru.yasdev.auth.dataBase.AuthDataBase
import ru.yasdev.sign_in.models.SignInState
import ru.yasdev.sign_up.data.SaveIdRepository

class SignInDataSource(context: Context, private val saveIdRepository: SaveIdRepository) {

    private val db =
        Room.databaseBuilder(
            context,
            AuthDataBase::class.java,
            Constants.DB_NAME
        ).build()

    suspend fun signIn(login: String, password: String): SignInState {
        val user = db.dao.getUserByPassword(login = login, password = password)
        return if (user != null) {
            saveIdRepository.saveId(user.login)
            SignInState.Success
        } else {
            SignInState.UserNotFound
        }
    }
}