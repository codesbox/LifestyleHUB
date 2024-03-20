package ru.yasdev.recommendations_feed.presentation

import android.location.Location
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
    val isPagination = MutableStateFlow(false)
    private val _location = MutableStateFlow<Location?>(null)

    fun updateLocation(location: Location?){
        _location.value = location
    }

    fun onEvent(feedEvent: FeedEvent){
        when(feedEvent){
            is FeedEvent.GetFeed -> {
                if (recommendationsFeedState.value is RecommendationsFeedState.Model){
                    getFeed(location = _location.value, (recommendationsFeedState.value as RecommendationsFeedState.Model).url)}
                else{
                    getFeed(location = _location.value, url = null)
                }
            }
            FeedEvent.NoPermissions -> {
                _recommendationsFeedState.value = RecommendationsFeedState.NoPermissions
            }
            FeedEvent.RefreshFeed -> {
                refreshFeed()
            }
        }
    }

    private fun getFeed(location: Location?, url: String?){
        viewModelScope.launch {
            val feedState = useCase.execute(location = location, url = url)
            if (feedState is RecommendationsFeedState.Model){
                if (recommendationsFeedState.value is RecommendationsFeedState.Model){
                    val combinedList = (recommendationsFeedState.value as RecommendationsFeedState.Model).list + feedState.list
                    _recommendationsFeedState.value = RecommendationsFeedState.Model(combinedList, feedState.url)
                    isPagination.value = false
                }
                else{
                    _recommendationsFeedState.value = feedState
                    isPagination.value = false
                }
            }
            else{
                _recommendationsFeedState.value = feedState
            }
        }
    }
    private fun refreshFeed(){
        viewModelScope.launch {
            _recommendationsFeedState.value = useCase.execute(_location.value, url = null)
        }
    }

}