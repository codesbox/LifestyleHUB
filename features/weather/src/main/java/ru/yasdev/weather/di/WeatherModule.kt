package ru.yasdev.weather.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.yasdev.weather.domain.GetWeatherUseCase
import ru.yasdev.weather.presentation.WeatherViewModel

val weatherModule = module {
    viewModelOf(::WeatherViewModel)
    factoryOf(::GetWeatherUseCase) { bind<GetWeatherUseCase>() }
}