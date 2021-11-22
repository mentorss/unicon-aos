package com.mentos.android.unicon.data.remote.common

import retrofit2.Response
import retrofit2.http.GET

interface CommonService {
    @GET("/app/aos")
    suspend fun getVersion(): Response<CommonResponse>
}
