package ru.yasdev.auth.sign_up.models

import kotlinx.serialization.Serializable

@Serializable
data class SignUpDTO(
    val name: Name, val login: Login, val picture: Image

)

@Serializable
data class Name(
    val first: String, val last: String
)

@Serializable
data class Login(
    val username: String
)

@Serializable
data class Image(
    val medium: String
)

