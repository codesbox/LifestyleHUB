package ru.yasdev.details.presentation

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
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.details.models.DetailsState

@Composable
fun DetailsScreen(id: String?) {

    val viewModel = koinViewModel<DetailsScreenViewModel>()
    val state = viewModel.detailsState.collectAsState().value
    if (id != null){
        LaunchedEffect(Unit) {
            if (state !is DetailsState.Details){
                viewModel.getDetails(id)
            }
        }
        when(state){
            is DetailsState.Details -> {
                Column {
                    Text(
                        text = state.title,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        modifier = Modifier.padding(start = 15.dp, top = 15.dp, end = 15.dp)
                    )
                    if (state.photoList.isNotEmpty()) {
                        LazyRow {
                            items(state.photoList){
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
                    Text(text = "Адрес: ${state.address}", modifier = Modifier.padding(horizontal = 15.dp))
                    LazyRow {
                        items(state.categories){
                            AssistChip(onClick = {  }, label = { Text(text = it) }, Modifier.padding(start = 15.dp, end = 15.dp, bottom = 15.dp))

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
    }
    else{
        Text(text = "Возникла непредвиденная ошибка")
    }



}