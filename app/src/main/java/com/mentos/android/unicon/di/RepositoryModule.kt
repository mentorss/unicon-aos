package com.mentos.android.unicon.di

import com.mentos.android.unicon.data.repository.FirebaseImageUploadRepository
import com.mentos.android.unicon.data.repository.SharedPrefRepository
import com.mentos.android.unicon.data.repository.network.AuthNetworkRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthNetworkRepository(get()) }
    single { SharedPrefRepository(androidContext()) }
    single { FirebaseImageUploadRepository() }
}