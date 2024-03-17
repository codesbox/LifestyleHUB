package ru.yasdev.lifestylehub.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import ru.yasdev.lifestylehub.di.appModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(listOf(appModule))
        }
    }
}