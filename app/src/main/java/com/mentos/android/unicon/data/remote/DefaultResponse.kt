package com.mentos.android.unicon.data.remote

import com.google.gson.annotations.SerializedName

data class DefaultResponse(
    @SerializedName(value = "isSuccess") val isSuccess : Boolean,
    @SerializedName(value = "code") val code : Int,
    @SerializedName(value = "message") val message : String,
)
