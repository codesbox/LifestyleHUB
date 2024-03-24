package ru.yasdev.planner.models

sealed interface PlannerState {
    data object Loading : PlannerState
    data class Planner(
        val list: List<EventModel>
    ) : PlannerState

    data object ErrorOnReceipt : PlannerState

    data object UserNotFound : PlannerState
}