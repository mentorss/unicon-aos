package com.mentos.android.unicon.di

import android.util.Log
import com.mentos.android.unicon.data.remote.auth.AuthService
import com.mentos.android.unicon.data.repository.SharedPrefRepository
import com.mentos.android.unicon.utils.GlobalConstant.Companion.X_ACCESS_TOKEN
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val PRODUCTION_URL = ""
const val TEST_URL = ""
private const val base_url: String = TEST_URL

fun getBaseUrl() = base_url

val networkModule: Module = module {
    fun provideHeaderInterceptor(sharedPrefRepository: SharedPrefRepository) =
        Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(X_ACCESS_TOKEN, "${sharedPrefRepository.jwtToken}")
                .build()
            Log.d("X_ACCESS_TOKEN", "${sharedPrefRepository.jwtToken}")
            chain.proceed(request)
        }

    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BODY }

    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ) = OkHttpClient.Builder()
        .readTimeout(5000, TimeUnit.MILLISECONDS)
        .connectTimeout(5000, TimeUnit.MILLISECONDS)
        .addInterceptor(headerInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()


    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    single { provideHeaderInterceptor(get()) }
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get(), get()) }
    single { provideRetrofit(get()) }
    single { provideAuthService(get()) }
}