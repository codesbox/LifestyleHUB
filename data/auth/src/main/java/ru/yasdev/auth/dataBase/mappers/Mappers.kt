package ru.yasdev.auth.dataBase.mappers

import ru.yasdev.auth.dataBase.models.AuthEntity
import ru.yasdev.auth.sign_up.models.SignUpDTO
import ru.yasdev.profile.models.ProfileState


fun AuthEntity.toProfileModel(): ProfileState.Profile {
    return ProfileState.Profile(
        firstName = firstName, lastName = lastName, image = image
    )
}

fun SignUpDTO.toAuthEntity(password: String): AuthEntity {
    return AuthEntity(
        login = login.username,
        password = password,
        firstName = name.first,
        lastName = name.last,
        image = picture.medium
    )
}


