package com.mentos.android.unicon.data.repository.network

import com.mentos.android.unicon.data.entities.User
import com.mentos.android.unicon.data.remote.auth.AuthResponse
import com.mentos.android.unicon.data.remote.auth.AuthService
import com.mentos.android.unicon.data.repository.BaseNetworkRepository

class AuthNetworkRepository(private val authService: AuthService) : BaseNetworkRepository(){
    suspend fun autoLogin(): AuthResponse {
        return apiRequest { authService.autoLogin() }
    }

    suspend fun login(user: User): AuthResponse {
        return apiRequest { authService.login(user) }
    }
}
