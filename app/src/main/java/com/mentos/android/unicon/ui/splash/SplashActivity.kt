package com.mentos.android.unicon.ui.splash

import android.util.Log
import androidx.annotation.LayoutRes
import com.mentos.android.unicon.R
import com.mentos.android.unicon.databinding.ActivitySplashBinding
import com.mentos.android.unicon.ui.BaseActivity
import com.mentos.android.unicon.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    private val splashViewModel: SplashViewModel by viewModel()

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_splash

    override fun initDataBinding() {
        binding.lifecycleOwner = this;
        binding.viewModel = splashViewModel

        splashViewModel.serverAccessible.observe(this, { serverAccessible ->
            if (serverAccessible) {
                splashViewModel.firstLaunchCheck()
            }
        })

        splashViewModel.firstLaunch.observe(this, { firstLaunch ->
            if (firstLaunch) {
//                startActivityWithClear(TeamCodeActivity::class.java)
//                startNextActivity(TutorialActivity::class.java)
                finish()
            } else {
                splashViewModel.autoLoginCheck()
            }
        })

        splashViewModel.autoLogin.observe(this, { isAutologin ->
            if (isAutologin) {
                startActivityWithClear(MainActivity::class.java)
            } else {
//                startNextActivity(TeamCodeActivity::class.java)
            }
            finish()
        })

        splashViewModel.fail.observe(this, { fail ->
//            341	"존재하지 않는 사용자입니다."
//            346	"해당 사원은 탈퇴회원입니다."
//            388	"JWT토큰을 입력해주세요."
//            389	"유효하지 않은 JWT토큰입니다."
//            402	"서버 긴급점검 중입니다."
//            404	"네트워크 오류가 발생했습니다."
            when(fail.code){
                341, 388, 389 -> {
                    showToast(fail.message)
                }
                402 -> {
                    showToast(getString(R.string.default_fail))
                }
                404 -> {
                    showToast(getString(R.string.default_fail))
                }
            }
            Log.d("네트워크: ", fail.message)
            finish()
        })
    }

    override fun initAfterBinding() {
        splashViewModel.serverVersionCheck()
//        splashViewModel.firstLaunchCheck()
    }
}