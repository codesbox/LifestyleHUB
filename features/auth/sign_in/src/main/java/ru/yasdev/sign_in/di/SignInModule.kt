package ru.yasdev.sign_in.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.yasdev.sign_in.domain.SignInUseCase
import ru.yasdev.sign_in.presentation.SignInViewModel

val signInModule = module {
    factoryOf(::SignInUseCase) { bind<SignInUseCase>() }
    viewModelOf(::SignInViewModel)
}