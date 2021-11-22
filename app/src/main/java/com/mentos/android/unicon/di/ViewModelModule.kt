package com.mentos.android.unicon.di

import com.mentos.android.unicon.ui.main.MainViewModel
import com.mentos.android.unicon.ui.main.home.HomeViewModel
import com.mentos.android.unicon.ui.main.match.MatchViewModel
import com.mentos.android.unicon.ui.main.mypage.MyPageViewModel
import com.mentos.android.unicon.ui.main.search.SearchViewModel
import com.mentos.android.unicon.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get(), get(), get()) }
    viewModel { MainViewModel() }
    viewModel { HomeViewModel() }
    viewModel { SearchViewModel() }
    viewModel { MatchViewModel() }
    viewModel { MyPageViewModel() }
}