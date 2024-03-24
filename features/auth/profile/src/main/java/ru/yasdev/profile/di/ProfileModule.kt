package ru.yasdev.profile.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.yasdev.profile.domain.GetUserUseCase
import ru.yasdev.profile.domain.LogOutUseCase
import ru.yasdev.profile.presentation.ProfileViewModel

val profileModule = module {
    viewModelOf(::ProfileViewModel)
    factoryOf(::GetUserUseCase) { bind<GetUserUseCase>() }
    factoryOf(::LogOutUseCase) { bind<LogOutUseCase>() }
}