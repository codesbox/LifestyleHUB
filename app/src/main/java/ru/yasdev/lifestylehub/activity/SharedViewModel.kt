package ru.yasdev.lifestylehub.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.yasdev.details.models.BaseDetails
import ru.yasdev.recommendations_feed.models.RecommendationModel

class SharedViewModel: ViewModel() {
    private val _baseDetails = MutableStateFlow<BaseDetails?>(null)
    val baseDetails = _baseDetails.asStateFlow()

    fun setBaseDetails(details: BaseDetails){
       _baseDetails.value = details
    }

}