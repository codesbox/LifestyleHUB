package ru.yasdev.auth.current_user_id

import ru.yasdev.sign_up.data.SaveIdRepository

class SaveIdRepositoryImpl(private val dataSource: SaveIdDataSource) : SaveIdRepository {
    override suspend fun saveId(id: String?) {
        dataSource.saveId(id)
    }
}