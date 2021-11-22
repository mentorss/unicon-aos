package com.mentos.android.unicon.data.repository.network

import com.mentos.android.unicon.data.remote.common.CommonResponse
import com.mentos.android.unicon.data.remote.common.CommonService
import com.mentos.android.unicon.data.repository.BaseNetworkRepository

class CommonNetworkRepository(private val commonService: CommonService) : BaseNetworkRepository() {
    suspend fun getVersion(): CommonResponse {
        return apiRequest { commonService.getVersion() }
    }
}
