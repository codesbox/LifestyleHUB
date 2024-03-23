package ru.yasdev.sign_in.presentation

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
import ru.yasdev.sign_in.models.SignInState

@Composable
fun SignInScreen(){
    val viewModel = koinViewModel<SignInViewModel>()
    val state = viewModel.signInState.collectAsState().value
    var login by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column {
        TextField(value = login, onValueChange = { newText ->
            login = newText
        })
        TextField(value = password, onValueChange = { newText ->
            password = newText
        })
        Button(onClick = {
            viewModel.signIn(login, password)
        }) {

        }
        when(state){
            SignInState.UserNotFound -> {
                Text(text = "Неверный логин или пароль")
            }
            SignInState.Loading -> {
                Text(text = "LOADING")
            }
            SignInState.SignIn -> {}
            SignInState.Success -> {
                Text(text = "Success")
            }
        }
    }
}