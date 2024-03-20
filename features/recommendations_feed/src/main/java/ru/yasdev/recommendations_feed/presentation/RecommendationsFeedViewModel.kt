package ru.yasdev.recommendations_feed.presentation

import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yasdev.recommendations_feed.domain.GetRecommendationsFeedUseCase
import ru.yasdev.recommendations_feed.models.FeedEvent
import ru.yasdev.recommendations_feed.models.RecommendationsFeedState

internal class RecommendationsFeedViewModel(
    private val useCase: GetRecommendationsFeedUseCase
): ViewModel() {

    private val _recommendationsFeedState = MutableStateFlow<RecommendationsFeedState>(RecommendationsFeedState.Loading)
    val recommendationsFeedState = _recommendationsFeedState.asStateFlow()
    val isRefresh = MutableStateFlow(false)
    val loc = MutableStateFlow<Location?>(null)

    fun zzz(location: Location?){
        loc.value = location
    }



    fun onFeedEvent(feedEvent: FeedEvent){
        when(feedEvent){
            is FeedEvent.GetFeed -> {
                if (recommendationsFeedState.value is RecommendationsFeedState.Model){
                    getFeed(location = loc.value, (recommendationsFeedState.value as RecommendationsFeedState.Model).url)}
                else{
                    getFeed(location = loc.value, url = null)
                }
            }

            FeedEvent.NoPermissions -> {
                _recommendationsFeedState.value = RecommendationsFeedState.NoPermissions
            }
            is FeedEvent.RefreshFeed -> {
                refreshFeed(feedEvent.location)
            }
        }
    }

    private fun getFeed(location: Location?, url: String?){
        viewModelScope.launch {
            val feedState = useCase.execute(location = location, url = url)
            if (feedState is RecommendationsFeedState.Model){
                if (recommendationsFeedState.value is RecommendationsFeedState.Model){
                    println(feedState.list)
                    val combinedList = (recommendationsFeedState.value as RecommendationsFeedState.Model).list + feedState.list
                    _recommendationsFeedState.value = RecommendationsFeedState.Model(combinedList, feedState.url)
                    isRefresh.value = false
                }

            }
            else{
                _recommendationsFeedState.value = feedState
            }
        }
    }
    private fun refreshFeed(location: Location?){
        viewModelScope.launch {
            _recommendationsFeedState.value = useCase.execute(location, url = null)
        }
    }

}