package ru.yasdev.recommendations_feed.models

import android.location.Location

internal sealed interface FeedEvent {
    data object NoPermissions : FeedEvent
    data class GetFeed(val location: Location?) : FeedEvent
    data class RefreshFeed(val location: Location?): FeedEvent
}