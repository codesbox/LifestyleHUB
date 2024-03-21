package ru.yasdev.details.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.StateFlow
import ru.yasdev.details.models.BaseDetails

@Composable
fun DetailsScreen(id: String?) {
    if (id != null){
        Text(text = id)
    }
    else{
        Text(text = "Возникла непредвиденная ошибка")
    }



}