package ru.yasdev.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yasdev.profile.domain.GetUserUseCase
import ru.yasdev.profile.domain.LogOutUseCase
import ru.yasdev.profile.models.ProfileState
import ru.yasdev.profile.navigation.ProfileNavigator

internal class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase, private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = getUserUseCase.execute()
        }
    }

    fun logOut(navigator: (ProfileNavigator) -> Unit) {
        viewModelScope.launch {
            logOutUseCase.execute()
            navigator(ProfileNavigator.ToBeginningGraph)
        }
    }


}