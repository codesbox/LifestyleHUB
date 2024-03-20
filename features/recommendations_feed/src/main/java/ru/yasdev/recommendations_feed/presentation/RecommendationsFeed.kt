package ru.yasdev.recommendations_feed.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.location.LocationServices
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.common.LocationState
import ru.yasdev.recommendations_feed.models.FeedEvent
import ru.yasdev.recommendations_feed.models.RecommendationsFeedState


@Composable
fun RecommendationsFeed(locationState: MutableState<LocationState>){

    val viewModel = koinViewModel<RecommendationsFeedViewModel>()
    var location: Location? = null
    val lazyColumnListState = rememberLazyListState()
    val isRefresh = viewModel.isRefresh.collectAsState()


        when(locationState.value){
            LocationState.Loading -> {}
            LocationState.NoPermissions -> {
                viewModel.onFeedEvent(FeedEvent.NoPermissions)
            }
            is LocationState.Model -> {
                location = (locationState.value as LocationState.Model).location
                viewModel.onFeedEvent(FeedEvent.RefreshFeed((locationState.value as LocationState.Model).location))
            }
        }

    val state = viewModel.recommendationsFeedState.collectAsState()

    when(state.value){
        RecommendationsFeedState.ErrorOnReceipt -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Ошибка при загрузке")
            }
        }
        RecommendationsFeedState.Loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
        }
        is RecommendationsFeedState.Model -> {
            LazyColumn(state = lazyColumnListState) {
                items((state.value as RecommendationsFeedState.Model).list){item->
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, top = 15.dp, end = 15.dp)
                    ) {
                        Text(text = item.title)
                        Image(
                            painter = rememberAsyncImagePainter(item.photoList[0]),
                            contentDescription = "null",
                            modifier = Modifier.size(200.dp)
                        )
                    }
                }
                item(key = (state.value as RecommendationsFeedState.Model).list.size){
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }
                }
                if(lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == (state.value as RecommendationsFeedState.Model).list.size){
                    viewModel.isRefresh.value = true
                }
            }
        }
        RecommendationsFeedState.NoPermissions -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Разрешите доступ к местоположению")
            }
        }

    }
    if(isRefresh.value){
        println("shdfjkshdjfhsjdhfjsdhjfhsdjf")
        viewModel.onFeedEvent(FeedEvent.GetFeed(location))


    }







}