package ru.yasdev.recommendations_feed.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.yasdev.recommendations_feed.RecommendationsFeedDataSource
import ru.yasdev.recommendations_feed.RecommendationsFeedRepositoryImpl
import ru.yasdev.recommendations_feed.data.RecommendationsFeedRepository

val recommendationsFeedDataModule = module {

    singleOf(::RecommendationsFeedDataSource) { bind<RecommendationsFeedDataSource>() }
    singleOf(::RecommendationsFeedRepositoryImpl) { bind<RecommendationsFeedRepository>() }
}