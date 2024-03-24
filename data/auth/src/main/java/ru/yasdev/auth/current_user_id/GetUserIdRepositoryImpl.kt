package ru.yasdev.auth.current_user_id

import ru.yasdev.common.GetUserIdRepository

class GetUserIdRepositoryImpl(
    private val dataSource: GetUserIdDataSource
) : GetUserIdRepository {
    override suspend fun getId(): String? {
        return dataSource.getId()
    }
}