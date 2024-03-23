package ru.yasdev.auth.profile

import android.content.Context
import androidx.room.Room
import ru.yasdev.auth.dataBase.AuthDataBase
import ru.yasdev.common.GetUserIdRepository
import ru.yasdev.profile.models.ProfileState

class ProfileDataSource(private val context: Context, private val getUserIdRepository: GetUserIdRepository) {

    private val db =
        Room.databaseBuilder(
            context,
            AuthDataBase::class.java,
            "auth.db"
        ).build()

    suspend fun getUser(): ProfileState{
        val userId = getUserIdRepository.getId()
        return if (userId != null){
            val userEntity = db.dao.getUserById(userId)
            if (userEntity == null){
                ProfileState.ErrorOnReceipt
            } else{
                ProfileState.Profile(firstName = userEntity.firstName, lastName = userEntity.lastName)
            }
        } else{
            ProfileState.ErrorOnReceipt
        }

    }

    suspend fun logOut(){
        db.userIdDao.deleteAll()
    }
}