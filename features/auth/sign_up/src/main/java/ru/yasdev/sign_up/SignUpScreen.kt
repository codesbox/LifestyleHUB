package ru.yasdev.sign_up

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.sign_up.models.SignUpState
import ru.yasdev.sign_up.navigation.SignUpNavigator

@Composable
fun SignUpScreen(navigator: (SignUpNavigator) -> Unit) {
    val viewModel = koinViewModel<SignUpViewModel>()
    val state = viewModel.signUpState.collectAsState().value
    var password by remember {
        mutableStateOf("")
    }
    Column {
        TextField(value = password, onValueChange = { newText ->
            password = newText
        })
        Button(onClick = {
            viewModel.signUp(password)
        }) {

        }
        when(state){
            SignUpState.ErrorOnReceipt -> {
                Text(text = "ERROR")
            }
            SignUpState.Loading -> {
                Text(text = "LOADING")
            }
            SignUpState.SignUp -> {}
            SignUpState.Success -> {
                navigator(SignUpNavigator.ToBeginningGraph)
            }
        }
        Button(onClick = { navigator(SignUpNavigator.PopBackStack) }) {
            Text(text = "Назад")
        }
    }
}