package ru.yasdev.auth

import java.security.MessageDigest

object PasswordHasher {
    fun hashPassword(password: String, salt: String): String {
        val bytes = (password + salt).toByteArray()
        val digest = MessageDigest.getInstance("SHA-256").digest(bytes)
        return digest.toHexString()
    }

    fun verifyPassword(password: String, hashedPassword: String, salt: String): Boolean {
        val newPasswordHash = hashPassword(password, salt)
        return newPasswordHash == hashedPassword
    }

    private fun ByteArray.toHexString(): String {
        return joinToString("") { "%02x".format(it) }
    }
}