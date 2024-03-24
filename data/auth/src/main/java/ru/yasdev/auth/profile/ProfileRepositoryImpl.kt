package ru.yasdev.auth.profile

import ru.yasdev.profile.data.ProfileRepository
import ru.yasdev.profile.models.ProfileState

class ProfileRepositoryImpl(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {
    override suspend fun getUser(): ProfileState {
        return profileDataSource.getUser()
    }

    override suspend fun logOut() {
        profileDataSource.logOut()
    }
}