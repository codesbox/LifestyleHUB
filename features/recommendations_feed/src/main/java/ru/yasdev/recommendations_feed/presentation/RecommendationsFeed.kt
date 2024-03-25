package ru.yasdev.recommendations_feed.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ru.yasdev.common.LocationState
import ru.yasdev.recommendations_feed.models.FeedEvent
import ru.yasdev.recommendations_feed.models.RecommendationsFeedState


fun LazyListScope.recommendationsFeed(
    locationState: MutableState<LocationState>,
    navigateToDetails: (id: String) -> Unit,
    viewModel: RecommendationsFeedViewModel,
    state: RecommendationsFeedState
) {

    when (locationState.value) {
        LocationState.Loading -> {}
        LocationState.NoPermissions -> {
            viewModel.onEvent(FeedEvent.NoPermissions)
        }

        is LocationState.Model -> {
            if (state !is RecommendationsFeedState.Model) {
                viewModel.updateLocation((locationState.value as LocationState.Model).location)
                viewModel.onEvent(FeedEvent.RefreshFeed)
            }
        }
    }

    when (state) {
        RecommendationsFeedState.ErrorOnReceipt -> {
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Ошибка при загрузке")
                }
            }

        }

        RecommendationsFeedState.Loading -> {
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                }
            }

        }

        is RecommendationsFeedState.Model -> {
            items(state.list) { item ->
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
                    Text(
                        text = "Адрес: ${item.address}",
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    LazyRow {
                        items(item.categories) {
                            AssistChip(
                                onClick = { },
                                label = { Text(text = it) },
                                Modifier.padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                            )

                        }

                    }
                }
            }
            item(key = state.list.size + 1) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                }
                viewModel.pagination()
            }
        }

        RecommendationsFeedState.NoPermissions -> {
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Разрешите доступ к местоположению")
                }
            }

        }

    }
}