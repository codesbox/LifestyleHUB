package ru.yasdev.sign_up.data

interface SaveIdRepository {
    suspend fun saveId(id: String?)

}