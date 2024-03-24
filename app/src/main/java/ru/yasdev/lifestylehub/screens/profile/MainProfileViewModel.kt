package ru.yasdev.lifestylehub.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yasdev.lifestylehub.screens.profile.domain.GetIdUseCase
import ru.yasdev.lifestylehub.screens.profile.models.MainProfileState

class MainProfileViewModel(
    private val getIdUseCase: GetIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MainProfileState>(MainProfileState.Loading)
    val state = _state.asStateFlow()

    fun setState() {
        viewModelScope.launch {
            val id = getIdUseCase.execute()
            if (id == null) {
                _state.value = MainProfileState.SignIn
            } else {
                _state.value = MainProfileState.Profile
            }
        }
    }
}