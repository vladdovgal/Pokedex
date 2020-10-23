package com.epam.pockedox

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initAndStartKoin()
    }

    private fun initAndStartKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@PokedexApplication)
            modules(listOf(viewModelsModule, repositoryModule))
        }
    }
}