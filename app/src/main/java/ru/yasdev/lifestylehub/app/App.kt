package ru.yasdev.lifestylehub.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import ru.yasdev.details.di.detailsDataModule
import ru.yasdev.details.di.detailsModule
import ru.yasdev.lifestylehub.di.appModule
import ru.yasdev.recommendations_feed.di.recommendationsFeedDataModule
import ru.yasdev.recommendations_feed.di.recommendationsFeedModule
import ru.yasdev.weather.di.weatherDataModule
import ru.yasdev.weather.di.weatherModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(listOf(appModule, weatherModule, weatherDataModule, recommendationsFeedDataModule, recommendationsFeedModule, detailsModule, detailsDataModule))
        }
    }
}