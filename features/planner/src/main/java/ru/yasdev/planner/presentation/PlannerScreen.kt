package ru.yasdev.planner.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.planner.models.NewEventModel
import ru.yasdev.planner.models.PlannerState

@Composable
fun PlannerScreen(navigateToDetails: (String) -> Unit) {
    Column {
        val viewModel = koinViewModel<PlannerViewModel>()
        val state = viewModel.state.collectAsState().value
        viewModel.getEvents()

        val openDialog = remember {
            mutableStateOf(false)
        }
        val title = remember {
            mutableStateOf<String?>(null)
        }
        val date = remember {
            mutableStateOf<String?>(null)
        }
        val note = remember {
            mutableStateOf<String?>(null)
        }
        if ((title.value != null) and (date.value != null) and (note.value != null)){
            viewModel.addEvent(NewEventModel(
                title = title.value!!,
                date = date.value!!,
                note = note.value!!,
                link = null
            ))
            title.value = null
            date.value = null
            note.value = null
        }
        if (openDialog.value){
            AddEventAlertDialog(openDialog, title, date, note)
        }

        Button(onClick = { openDialog.value = true }) {
            Text(text = "Добавить досуг")
        }

        when(state){
            PlannerState.ErrorOnReceipt -> {
                Text(text = "Ошибка при получении")
            }
            PlannerState.Loading -> {
                Text(text = "Загрузка")
            }
            is PlannerState.Planner -> {
                LazyColumn {
                    items(state.list){item ->
                        Card(Modifier.padding(start = 15.dp, top = 15.dp, end = 15.dp)) {
                            Text(text = item.date)
                            Text(text = item.title)
                            if(item.link != null){
                                Button(onClick = { navigateToDetails(item.link) }) {

                                }
                            }
                            if(item.note != null){
                                Text(text = item.note)
                            }
                            Button(onClick = { viewModel.deleteEvent(item.eventId) }) {
                                Text(text = "Удалить досуг")
                            }

                        }
                    }
                }
            }
            PlannerState.UserNotFound -> {
                Text(text = "Пожалуйста, авторезуйтесь")
            }
        }
    }
    

    
}