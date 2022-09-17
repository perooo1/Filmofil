package com.plenart.newfilmofil

import android.app.Application
import com.plenart.newfilmofil.di.apiModule
import com.plenart.newfilmofil.di.databaseModule
import com.plenart.newfilmofil.di.repositoryModule
import com.plenart.newfilmofil.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewFilmofil: Application() {
    override fun onCreate() {
        super.onCreate()
        application = this

        //TODO start koin

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@NewFilmofil)
            modules(apiModule, databaseModule, repositoryModule ,viewModelModule)

        }

    }

    companion object {
        lateinit var application: Application
    }
}