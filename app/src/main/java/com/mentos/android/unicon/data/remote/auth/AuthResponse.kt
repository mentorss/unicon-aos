package com.mentos.android.unicon.data.remote.auth

import com.google.gson.annotations.SerializedName
import com.mentos.android.unicon.data.entities.Auth

data class AuthResponse(
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
    @SerializedName(value = "result") val auth : Auth?
)
