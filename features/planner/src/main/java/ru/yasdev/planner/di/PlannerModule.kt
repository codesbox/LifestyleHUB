package ru.yasdev.planner.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.yasdev.planner.domain.AddEventUseCase
import ru.yasdev.planner.domain.DeleteEventUseCase
import ru.yasdev.planner.domain.GetEventsUseCase
import ru.yasdev.planner.presentation.PlannerViewModel

val plannerModule = module {
    viewModelOf(::PlannerViewModel)
    factoryOf(::AddEventUseCase) { bind<AddEventUseCase>() }
    factoryOf(::DeleteEventUseCase) { bind<DeleteEventUseCase>() }
    factoryOf(::GetEventsUseCase) { bind<GetEventsUseCase>() }
}