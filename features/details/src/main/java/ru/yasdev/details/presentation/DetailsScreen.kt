package ru.yasdev.details.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.details.models.AddButtonState
import ru.yasdev.details.models.AddEventModel
import ru.yasdev.details.models.DetailsState


@Composable
fun DetailsScreen(id: String?) {
    val viewModel = koinViewModel<DetailsScreenViewModel>()
    val state = viewModel.detailsState.collectAsState().value
    val buttonState = viewModel.addButtonState.collectAsState().value

    if (id != null) {
        LaunchedEffect(Unit) {
            if (state !is DetailsState.Details) {
                viewModel.getDetails(id)
            }
        }
        when (state) {
            is DetailsState.Details -> {
                Column {
                    Text(
                        text = state.title,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        modifier = Modifier.padding(start = 15.dp, top = 15.dp, end = 15.dp)
                    )
                    if (state.photoList.isNotEmpty()) {
                        LazyRow {
                            items(state.photoList) {
                                Image(
                                    painter = rememberAsyncImagePainter(it),
                                    contentDescription = "null",
                                    Modifier
                                        .width(400.dp)
                                        .padding(15.dp)
                                        .clip(RoundedCornerShape(25.dp))
                                        .aspectRatio(1f)
                                )

                            }

                        }
                    }
                    Text(
                        text = "Адрес: ${state.address}",
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    LazyRow {
                        items(state.categories) {
                            AssistChip(
                                onClick = { },
                                label = { Text(text = it) },
                                Modifier.padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                            )

                        }

                    }
                    val openDialog = remember {
                        mutableStateOf(false)
                    }
                    val title = remember {
                        mutableStateOf<String?>(null)
                    }
                    val date = remember {
                        mutableStateOf<String?>(null)
                    }
                    if ((title.value != null) and (date.value != null)) {
                        viewModel.addEvent(
                            AddEventModel(
                                title = title.value!!,
                                date = date.value!!,
                                link = state.id,
                            )
                        )
                        title.value = null
                        date.value = null
                    }
                    if (openDialog.value) {
                        AddEventDetailsAlertDialog(
                            openDialog = openDialog, title = title, date = date
                        )
                    }
                    val context = LocalContext.current
                    when(buttonState){
                        AddButtonState.NoAuth -> {
                            Button(onClick = {
                                Toast.makeText(context,"Необходима авторизация",Toast.LENGTH_SHORT).show() }) {
                                Text(text = "Добавить в планнер")
                            }
                        }
                        AddButtonState.Ok -> {
                            Button(onClick = { openDialog.value = true }) {
                                Text(text = "Добавить в планнер")
                            }
                        }
                    }


                }
            }

            DetailsState.ErrorOnReceipt -> {
                Box(
                    Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Ошибка при загрузке")
                }
            }

            DetailsState.Loading -> {
                Box(
                    Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                }
            }
        }
    } else {
        Text(text = "Возникла непредвиденная ошибка")
    }


}