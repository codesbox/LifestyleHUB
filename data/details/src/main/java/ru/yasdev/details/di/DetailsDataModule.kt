package ru.yasdev.details.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.yasdev.details.cache.CacheStorage
import ru.yasdev.details.remote.DetailsDataSource
import ru.yasdev.details.remote.DetailsRepositoryImpl
import ru.yasdev.details.data.DetailsRepository

val detailsDataModule = module {
    singleOf(::DetailsRepositoryImpl) { bind<DetailsRepository>() }
    singleOf(::DetailsDataSource) { bind<DetailsDataSource>() }
    singleOf(::CacheStorage) { bind<CacheStorage>() }
}