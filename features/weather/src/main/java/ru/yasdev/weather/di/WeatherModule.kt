package ru.yasdev.weather.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.yasdev.weather.presentation.WeatherViewModel

val weatherModule = module {
    viewModelOf(::WeatherViewModel)
}