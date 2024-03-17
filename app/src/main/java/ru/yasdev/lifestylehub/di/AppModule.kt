package ru.yasdev.lifestylehub.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.yasdev.lifestylehub.screens.home.HomeViewModel

val appModule = module {
    viewModelOf(::HomeViewModel)
}