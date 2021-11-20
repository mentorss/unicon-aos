package com.mentos.android.unicon.data.entities

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName(value = "id") val id: String = "",
    @SerializedName(value = "password") val pw: String = "",
)
