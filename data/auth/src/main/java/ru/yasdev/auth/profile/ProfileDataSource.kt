package ru.yasdev.auth.profile

import android.content.Context
import androidx.room.Room
import ru.yasdev.auth.Constants
import ru.yasdev.auth.dataBase.AuthDataBase
import ru.yasdev.auth.dataBase.mappers.toProfileModel
import ru.yasdev.common.GetUserIdRepository
import ru.yasdev.profile.models.ProfileState

class ProfileDataSource(context: Context, private val getUserIdRepository: GetUserIdRepository) {

    private val db = Room.databaseBuilder(
        context, AuthDataBase::class.java, Constants.DB_NAME
    ).build()

    suspend fun getUser(): ProfileState {
        val userId = getUserIdRepository.getId()
        return if (userId != null) {
            val authEntity = db.dao.getUserById(userId)
            authEntity?.toProfileModel() ?: ProfileState.ErrorOnReceipt
        } else {
            ProfileState.ErrorOnReceipt
        }
    }

    suspend fun logOut() {
        db.userIdDao.deleteAll()
    }
}