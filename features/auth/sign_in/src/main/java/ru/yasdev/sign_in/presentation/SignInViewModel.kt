package ru.yasdev.sign_in.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yasdev.sign_in.domain.SignInUseCase
import ru.yasdev.sign_in.models.SignInState

internal class SignInViewModel(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _signInState = MutableStateFlow<SignInState>(SignInState.SignIn)
    val signInState = _signInState.asStateFlow()

    fun signIn(login: String, password: String) {
        _signInState.value = SignInState.Loading
        viewModelScope.launch {
            _signInState.value = signInUseCase.execute(login = login, password = password)
        }
    }

}