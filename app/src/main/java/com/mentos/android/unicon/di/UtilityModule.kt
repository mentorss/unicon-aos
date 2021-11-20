package com.mentos.android.unicon.di

import com.mentos.android.unicon.utils.GlobalConstant
import org.koin.dsl.module

val utilityModule = module {
    single { GlobalConstant() }
}