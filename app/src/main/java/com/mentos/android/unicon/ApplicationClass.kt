package com.mentos.android.unicon

import android.app.Application
import com.mentos.android.unicon.di.networkModule
import com.mentos.android.unicon.di.repositoryModule
import com.mentos.android.unicon.di.utilityModule
import com.mentos.android.unicon.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.BuildConfig.DEBUG
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ApplicationClass : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            if (DEBUG) {
                androidLogger()
            } else {
            androidLogger(Level.NONE)
        }
            androidContext(this@ApplicationClass)
            modules(
                utilityModule,
                viewModelModule,
                networkModule,
                repositoryModule
            )
        }
    }
}