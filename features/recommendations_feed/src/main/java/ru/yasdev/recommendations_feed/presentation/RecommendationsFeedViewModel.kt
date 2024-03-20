package ru.yasdev.recommendations_feed.presentation

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.yasdev.recommendations_feed.domain.GetRecommendationsFeedUseCase

internal class RecommendationsFeedViewModel(
    private val useCase: GetRecommendationsFeedUseCase
): ViewModel() {

    fun test(location: Location?){
        viewModelScope.launch {
            useCase.execute(location)
        }
    }

}