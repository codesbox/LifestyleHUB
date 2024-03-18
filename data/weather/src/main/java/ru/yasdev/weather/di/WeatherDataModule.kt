package ru.yasdev.weather.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.yasdev.weather.WeatherDataSource
import ru.yasdev.weather.WeatherRepositoryImpl
import ru.yasdev.weather.data.WeatherRepository


val weatherDataModule = module {


     single<WeatherRepository> { WeatherRepositoryImpl(get()) }
     single<WeatherDataSource> { WeatherDataSource() }

}