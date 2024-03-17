package ru.yasdev.lifestylehub.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.yasdev.lifestylehub.navigation.Destinations

@SuppressLint("RestrictedApi")
@Composable
fun HomeScreen(navController: NavController) {


    val viewModel: HttpResponseScreenViewModel = viewModel(key = "DHJHDSJDJS")

    Box {
        Row{
            Column {
                val a = viewModel.counter.collectAsState()
                Text(text = "firstScreen")
                Text(text = a.value)
                TextField(value = a.value, onValueChange = {newText -> viewModel.incrementCounter(newText)}, modifier = Modifier.background(
                    Color.Transparent))
                Button(onClick = { navController.navigate(Destinations.DetailsScreenRoute.route)  }) {

                }

            }
        }

    }

}

class HttpResponseScreenViewModel: ViewModel() {

    private val _counter = MutableStateFlow("")
    val counter = _counter.asStateFlow()

    fun incrementCounter(text: String) {
        _counter.value = text
    }
}
