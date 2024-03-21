package ru.yasdev.recommendations_feed.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.common.LocationState
import ru.yasdev.recommendations_feed.models.FeedEvent
import ru.yasdev.recommendations_feed.models.RecommendationModel
import ru.yasdev.recommendations_feed.models.RecommendationsFeedState


@Composable
fun RecommendationsFeed(
    locationState: MutableState<LocationState>,
    navigateToDetails: (id: String) -> Unit
) {

    val viewModel = koinViewModel<RecommendationsFeedViewModel>()
    val lazyColumnListState = rememberLazyListState()
    val state = viewModel.recommendationsFeedState.collectAsState()
    val isPagination = viewModel.isPagination.collectAsState()

    when (locationState.value) {
        LocationState.Loading -> {}
        LocationState.NoPermissions -> {
            viewModel.onEvent(FeedEvent.NoPermissions)
        }

        is LocationState.Model -> {
            if (state.value !is RecommendationsFeedState.Model) {
                viewModel.updateLocation((locationState.value as LocationState.Model).location)
                viewModel.onEvent(FeedEvent.RefreshFeed)
            }

        }
    }



    when (state.value) {
        RecommendationsFeedState.ErrorOnReceipt -> {
            Box(
                Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(text = "Ошибка при загрузке")
            }
        }

        RecommendationsFeedState.Loading -> {
            Box(
                Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
        }

        is RecommendationsFeedState.Model -> {
            Feed(
                state = state.value,
                viewModel = viewModel,
                lazyColumnListState = lazyColumnListState,
                navigateToDetails
            )
        }

        RecommendationsFeedState.NoPermissions -> {
            Box(
                Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(text = "Разрешите доступ к местоположению")
            }
        }

    }
    if (isPagination.value) {
        viewModel.onEvent(FeedEvent.GetFeed)
    }
}

@Composable
private fun Feed(
    state: RecommendationsFeedState,
    viewModel: RecommendationsFeedViewModel,
    lazyColumnListState: LazyListState,
    navigateToDetails: (id: String) -> Unit
) {
    LazyColumn(state = lazyColumnListState) {
        items((state as RecommendationsFeedState.Model).list) { item ->
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, top = 15.dp, end = 15.dp)
                    .clickable {
                               navigateToDetails(item.id)
                    },
            ) {
                Text(
                    text = item.title,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    modifier = Modifier.padding(start = 15.dp, top = 15.dp, end = 15.dp)
                )
                if (item.photoList.isNotEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(item.photoList[0]),
                        contentDescription = "null",
                        Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .clip(RoundedCornerShape(25.dp))
                            .aspectRatio(1f)
                    )
                }
                Text(text = "Адрес: ${item.address}", modifier = Modifier.padding(horizontal = 15.dp))
                LazyRow {
                    items(item.categories){
                        AssistChip(onClick = {  }, label = { Text(text = it) }, Modifier.padding(start = 15.dp, end = 15.dp, bottom = 15.dp))

                    }

                }

            }
        }
        item(key = state.list.size) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
        }
        if (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == state.list.size) {
            viewModel.isPagination.value = true
        }
    }
}