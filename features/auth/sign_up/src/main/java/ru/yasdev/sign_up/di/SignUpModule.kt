package ru.yasdev.sign_up.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.yasdev.sign_up.SignUpViewModel
import ru.yasdev.sign_up.domain.SignUpUseCase

val signUpModule = module {
    factoryOf(::SignUpUseCase) { bind<SignUpUseCase>() }
    viewModelOf(::SignUpViewModel)
}