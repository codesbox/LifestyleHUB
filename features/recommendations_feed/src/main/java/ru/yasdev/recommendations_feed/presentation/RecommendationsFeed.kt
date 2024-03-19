package ru.yasdev.recommendations_feed.presentation

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecommendationsFeed(){

    val viewModel = koinViewModel<RecommendationsFeedViewModel>()

}