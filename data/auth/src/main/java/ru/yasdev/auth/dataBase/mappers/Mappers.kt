package ru.yasdev.auth.dataBase.mappers

import ru.yasdev.auth.dataBase.models.AuthEntity
import ru.yasdev.auth.sign_up.models.SignUpDTO
import ru.yasdev.profile.models.ProfileState


fun AuthEntity.toProfileModel(): ProfileState.Profile {
    return ProfileState.Profile(
        firstName = firstName, lastName = lastName, image = image, id = login
    )
}

fun SignUpDTO.toAuthEntity(hashPassword: String, salt: String): AuthEntity {
    return AuthEntity(
        login = login.username,
        hashPassword = hashPassword,
        firstName = name.first,
        lastName = name.last,
        image = picture.medium,
        salt = salt
    )
}


