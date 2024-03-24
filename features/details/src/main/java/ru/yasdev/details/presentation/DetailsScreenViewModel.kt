package ru.yasdev.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yasdev.details.domain.AddEventUseCase
import ru.yasdev.details.domain.GetDetailsUseCase
import ru.yasdev.details.domain.GetUserIdUseCase
import ru.yasdev.details.models.AddButtonState
import ru.yasdev.details.models.AddEventModel
import ru.yasdev.details.models.DetailsState

class DetailsScreenViewModel(
    private val getDetailsUseCase: GetDetailsUseCase,
    private val addEventUseCase: AddEventUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
): ViewModel() {

    private val _detailsState = MutableStateFlow<DetailsState>(DetailsState.Loading)
    val detailsState = _detailsState.asStateFlow()

    private val _addButtonState = MutableStateFlow<AddButtonState>(AddButtonState.NoAuth)
    val addButtonState = _addButtonState.asStateFlow()

    fun getDetails(id: String){
        viewModelScope.launch {
            _detailsState.value = getDetailsUseCase.execute(id)
        }

    }
    fun addEvent(model: AddEventModel){
        viewModelScope.launch{
            addEventUseCase.execute(model)
        }
    }
    init {
        viewModelScope.launch {
            val userId = getUserIdUseCase.execute()
            if(userId != null){
                _addButtonState.value = AddButtonState.Ok
            }
        }

    }



}