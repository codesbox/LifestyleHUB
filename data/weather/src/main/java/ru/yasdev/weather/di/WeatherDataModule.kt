package ru.yasdev.weather.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.yasdev.weather.WeatherDataSourceImpl
import ru.yasdev.weather.WeatherRepositoryImpl
import ru.yasdev.weather.data.WeatherRepository


val weatherDataModule = module {
    singleOf(::WeatherRepositoryImpl) { bind<WeatherRepository>() }
    singleOf(::WeatherDataSourceImpl) { bind<WeatherDataSourceImpl>() }
    singleOf(::WeatherDataSourceImpl) { bind<WeatherDataSourceImpl>() }
    single<HttpClient> { HttpClient(OkHttp) }
}