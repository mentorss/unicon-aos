package com.mentos.android.unicon.data.remote.auth

import com.mentos.android.unicon.data.entities.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @GET("/auto-login")
    suspend fun autoLogin(): Response<AuthResponse>

    @POST("/login")
    suspend fun login(@Body user: User): Response<AuthResponse>
}