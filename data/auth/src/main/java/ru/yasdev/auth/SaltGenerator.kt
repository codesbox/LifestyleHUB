package ru.yasdev.auth

import java.security.SecureRandom

object SaltGenerator {
    private const val SALT_LENGTH = 16

    private fun generateSalt(): ByteArray {
        val random = SecureRandom()
        val salt = ByteArray(SALT_LENGTH)
        random.nextBytes(salt)
        return salt
    }

    fun generateSaltString(): String {
        return generateSalt().toHexString()
    }

    private fun ByteArray.toHexString(): String {
        return joinToString("") { "%02x".format(it) }
    }
}