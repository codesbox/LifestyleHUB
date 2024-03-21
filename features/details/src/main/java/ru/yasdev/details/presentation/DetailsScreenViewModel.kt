package ru.yasdev.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yasdev.details.domain.GetDetailsUseCase
import ru.yasdev.details.models.DetailsState

class DetailsScreenViewModel(
    private val getDetailsUseCase: GetDetailsUseCase
): ViewModel() {

    private val _detailsState = MutableStateFlow<DetailsState>(DetailsState.Loading)
    val detailsState = _detailsState.asStateFlow()

    fun getDetails(id: String){
        viewModelScope.launch {
            _detailsState.value = getDetailsUseCase.execute(id)
        }

    }



}