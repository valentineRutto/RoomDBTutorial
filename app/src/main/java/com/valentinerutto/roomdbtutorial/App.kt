package com.valentinerutto.roomdbtutorial

import android.app.Application
import com.valentinerutto.roomdbtutorial.di.databaseModule
import com.valentinerutto.roomdbtutorial.di.networkingModule
import com.valentinerutto.roomdbtutorial.di.repositoryModule
import com.valentinerutto.roomdbtutorial.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    companion object {
        lateinit var INSTANCE: App
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        val modules = listOf(repositoryModule, networkingModule, databaseModule, viewmodelModule)

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@App)
            modules(modules)
        }
    }
}