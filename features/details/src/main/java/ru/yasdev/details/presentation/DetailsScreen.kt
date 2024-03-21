package ru.yasdev.details.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.StateFlow
import ru.yasdev.details.models.BaseDetails

@Composable
fun DetailsScreen(baseDetails: StateFlow<BaseDetails?>) {
    val details = baseDetails.collectAsState()
    if (details.value != null){
        Text(text = details.value!!.title)
    }
    else{
        Text(text = "Загрузка")
    }



}