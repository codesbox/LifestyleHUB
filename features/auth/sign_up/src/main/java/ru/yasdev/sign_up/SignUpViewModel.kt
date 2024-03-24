package ru.yasdev.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yasdev.sign_up.domain.SignUpUseCase
import ru.yasdev.sign_up.models.SignUpState

internal class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.SignUp)
    val signUpState = _signUpState.asStateFlow()

    fun signUp(password: String) {
        _signUpState.value = SignUpState.Loading
        viewModelScope.launch {
            _signUpState.value = signUpUseCase.execute(password)
        }
    }

}