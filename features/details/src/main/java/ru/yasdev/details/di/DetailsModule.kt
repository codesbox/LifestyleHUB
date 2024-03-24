package ru.yasdev.details.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.yasdev.details.domain.AddEventUseCase
import ru.yasdev.details.domain.GetDetailsUseCase
import ru.yasdev.details.domain.GetUserIdUseCase
import ru.yasdev.details.presentation.DetailsScreenViewModel

val detailsModule = module {
    factoryOf(::GetDetailsUseCase) { bind<GetDetailsUseCase>() }
    factoryOf(::AddEventUseCase) { bind<AddEventUseCase>() }
    factoryOf(::GetUserIdUseCase) { bind<GetUserIdUseCase>() }
    viewModelOf(::DetailsScreenViewModel)
}