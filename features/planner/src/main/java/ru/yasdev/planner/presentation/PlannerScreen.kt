package ru.yasdev.planner.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
        if ((title.value != null) and (date.value != null) and (note.value != null)) {
            viewModel.addEvent(
                NewEventModel(
                    title = title.value!!, date = date.value!!, note = note.value!!, link = null
                )
            )
            title.value = null
            date.value = null
            note.value = null
        }
        if (openDialog.value) {
            AddEventAlertDialog(openDialog, title, date, note)
        }

        when (state) {
            PlannerState.ErrorOnReceipt -> {
                Box(
                    Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Ошибка при получении")
                }
            }

            PlannerState.Loading -> {
                Box(
                    Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                }
            }

            is PlannerState.Planner -> {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Мой досуг",
                            Modifier.padding(15.dp),
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = { openDialog.value = true }, Modifier.padding(15.dp)
                        ) {
                            Text(text = "Добавить досуг")
                        }
                    }

                }
                LazyColumn {
                    items(state.list) { item ->
                        Card(
                            Modifier
                                .padding(start = 15.dp, bottom = 15.dp, end = 15.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp)
                            ) {
                                Text(
                                    text = item.title,
                                    Modifier.weight(1f),
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                                )
                                OutlinedButton(onClick = { viewModel.deleteEvent(item.eventId) }) {
                                    Text(text = "Удалить досуг")
                                }
                            }
                            Text(text = item.date, Modifier.padding(15.dp))
                            if (item.link != null) {
                                OutlinedButton(
                                    onClick = { navigateToDetails(item.link) },
                                    Modifier.padding(15.dp)
                                ) {
                                    Text(text = "Детали")
                                }
                            }
                            if (item.note != null) {
                                Text(text = item.note, Modifier.padding(15.dp))
                            }

                        }
                    }
                }
            }

            PlannerState.UserNotFound -> {
                Box(
                    Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Необходима авторизация")
                }
            }
        }
    }


}