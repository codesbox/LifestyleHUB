package ru.yasdev.common

import android.location.Location

sealed interface LocationState {
    data object Loading : LocationState
    data object NoPermissions : LocationState
    data class Model(val location: Location?) : LocationState
}