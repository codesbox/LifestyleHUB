package ru.yasdev.planner.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yasdev.planner.domain.AddEventUseCase
import ru.yasdev.planner.domain.DeleteEventUseCase
import ru.yasdev.planner.domain.GetEventsUseCase
import ru.yasdev.planner.models.NewEventModel
import ru.yasdev.planner.models.PlannerState

class PlannerViewModel(
    private val addEventUseCase: AddEventUseCase,
    private val deleteEventUseCase: DeleteEventUseCase,
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PlannerState>(PlannerState.Loading)
    val state = _state.asStateFlow()
    fun getEvents() {
        viewModelScope.launch {
            _state.value = getEventsUseCase.execute()
        }
    }

    fun deleteEvent(eventId: Int) {
        viewModelScope.launch {
            deleteEventUseCase.execute(eventId)
            _state.value = getEventsUseCase.execute()
        }
    }

    fun addEvent(eventModel: NewEventModel) {
        viewModelScope.launch {
            addEventUseCase.execute(eventModel)
            _state.value = getEventsUseCase.execute()
        }

    }


}