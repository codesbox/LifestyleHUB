package ru.yasdev.lifestylehub.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.yasdev.lifestylehub.screens.profile.MainProfileViewModel
import ru.yasdev.lifestylehub.screens.profile.domain.GetIdUseCase

val appModule = module {
    viewModelOf(::MainProfileViewModel)
    factoryOf(::GetIdUseCase) { bind<GetIdUseCase>() }
}