package com.mentos.android.unicon.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.android.unicon.data.remote.Fail
import com.mentos.android.unicon.data.repository.SharedPrefRepository
import com.mentos.android.unicon.data.repository.network.AuthNetworkRepository
import com.mentos.android.unicon.data.repository.network.CommonNetworkRepository
import com.mentos.android.unicon.utils.ApiException
import kotlinx.coroutines.launch


class SplashViewModel(
    private val sharedPrefRepository: SharedPrefRepository,
    private val authNetworkRepository: AuthNetworkRepository,
    private val commonNetworkRepository: CommonNetworkRepository
) : ViewModel() {

    var serverAccessible: MutableLiveData<Boolean> = MutableLiveData()
    var firstLaunch: MutableLiveData<Boolean> = MutableLiveData()
    var autoLogin: MutableLiveData<Boolean> = MutableLiveData()
    var fail: MutableLiveData<Fail> = MutableLiveData()

    fun autoLoginCheck() {
        val jwtToken = sharedPrefRepository.jwtToken
        if (jwtToken != null) {
            tokenCheck()
        } else {
            autoLogin.value = false
        }
    }

    private fun tokenCheck() = viewModelScope.launch {
        try {
            val authResponse = authNetworkRepository.autoLogin()
            if (authResponse.isSuccess) {
                autoLogin.value = true
            } else {
                fail.value = Fail(authResponse.message, authResponse.code)
            }
        } catch (e: ApiException) {
            fail.value = Fail(e.message!!, 404)
        } catch (e: Exception) {
            fail.value = Fail(e.message!!, 404)
        }
    }

    fun serverVersionCheck() = viewModelScope.launch {
        try {
            val authResponse = commonNetworkRepository.getVersion()
            if (authResponse.code == 200) {
                authResponse.auth?.let {
                    serverAccessible.value = true
                }
            } else {
                fail.value = Fail(authResponse.message, authResponse.code)
            }
        } catch (e: ApiException) {
            fail.value = Fail(e.message!!, 404)
        } catch (e: Exception) {
            fail.value = Fail(e.message!!, 404)
        }
    }

    fun firstLaunchCheck() {
        val isFirstLaunch = sharedPrefRepository.isFirstLaunch
        firstLaunch.value = isFirstLaunch
    }
}