package ru.yasdev.profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.profile.models.ProfileState
import ru.yasdev.profile.navigation.ProfileNavigator

@Composable
fun ProfileScreen(navigator: (ProfileNavigator) -> Unit) {
    Column {

        val viewModel = koinViewModel<ProfileViewModel>()
        val state = viewModel.state.collectAsState().value
        when(state){
            ProfileState.ErrorOnReceipt -> {
                Text(text = "ошибка")
            }
            ProfileState.Loading -> {
                Text(text = "загрузка")
            }
            is ProfileState.Profile -> {
                Text(text = state.firstName)
                Text(text = state.lastName)
            }
        }
        Button(onClick = { viewModel.logOut(navigator) }) {
            Text(text = "выйти")
        }
    }


}