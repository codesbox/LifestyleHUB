package ru.yasdev.common

interface GetUserIdRepository {
    suspend fun getId(): String?
}