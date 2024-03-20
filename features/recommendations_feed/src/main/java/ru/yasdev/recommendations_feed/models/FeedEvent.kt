package ru.yasdev.recommendations_feed.models

import android.location.Location

internal sealed interface FeedEvent {
    data object NoPermissions : FeedEvent
    data object GetFeed : FeedEvent
    data class RefreshFeed(val location: Location?): FeedEvent
}