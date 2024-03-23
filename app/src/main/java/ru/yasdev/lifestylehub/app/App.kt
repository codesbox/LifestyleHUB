package ru.yasdev.lifestylehub.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import ru.yasdev.auth.di.authDataModule
import ru.yasdev.details.di.detailsDataModule
import ru.yasdev.details.di.detailsModule
import ru.yasdev.lifestylehub.di.appModule
import ru.yasdev.planner.di.plannerDataModule
import ru.yasdev.planner.di.plannerModule
import ru.yasdev.profile.di.profileModule
import ru.yasdev.recommendations_feed.di.recommendationsFeedDataModule
import ru.yasdev.recommendations_feed.di.recommendationsFeedModule
import ru.yasdev.sign_in.di.signInModule
import ru.yasdev.sign_up.di.signUpModule
import ru.yasdev.weather.di.weatherDataModule
import ru.yasdev.weather.di.weatherModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    weatherModule,
                    weatherDataModule,
                    recommendationsFeedDataModule,
                    recommendationsFeedModule,
                    detailsModule,
                    detailsDataModule,
                    signInModule,
                    signUpModule,
                    profileModule,
                    authDataModule,
                    plannerModule,
                    plannerDataModule
                )
            )
        }
    }
}