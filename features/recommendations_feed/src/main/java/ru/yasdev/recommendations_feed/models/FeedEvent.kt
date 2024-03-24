package ru.yasdev.recommendations_feed.models

sealed interface FeedEvent {
    data object NoPermissions : FeedEvent
    data object GetFeed : FeedEvent
    data object RefreshFeed : FeedEvent
}