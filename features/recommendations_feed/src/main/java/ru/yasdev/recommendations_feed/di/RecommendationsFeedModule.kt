package ru.yasdev.recommendations_feed.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.yasdev.recommendations_feed.domain.GetRecommendationsFeedUseCase
import ru.yasdev.recommendations_feed.presentation.RecommendationsFeedViewModel

val recommendationsFeedModule = module {
    viewModelOf(::RecommendationsFeedViewModel)
    factoryOf(::GetRecommendationsFeedUseCase) { bind<GetRecommendationsFeedUseCase>() }
}