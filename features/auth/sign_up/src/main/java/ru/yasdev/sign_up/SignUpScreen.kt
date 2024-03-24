package ru.yasdev.sign_up

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
    Box(
        Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.sign_up),
                    Modifier.padding(15.dp),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(value = password,
                    onValueChange = { newText ->
                        password = newText
                    },
                    Modifier.padding(15.dp),
                    label = @Composable { Text(text = stringResource(id = R.string.password)) })
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(onClick = {
                    viewModel.signUp(password)
                }, Modifier.padding(15.dp)) {
                    Text(text = stringResource(id = R.string.sign_up))
                }
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                when (state) {
                    SignUpState.ErrorOnReceipt -> {
                        Text(text = stringResource(id = R.string.error), Modifier.padding(15.dp))
                    }

                    SignUpState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }

                    SignUpState.SignUp -> {}
                    SignUpState.Success -> {
                        navigator(SignUpNavigator.ToBeginningGraph)
                    }
                }
            }

            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.entrance),
                    Modifier
                        .padding(15.dp)
                        .clickable { navigator(SignUpNavigator.PopBackStack) })
            }
        }
    }

}