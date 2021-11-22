package com.mentos.android.unicon.ui.main.mypage

import com.mentos.android.unicon.R
import com.mentos.android.unicon.databinding.FragmentMypageBinding
import com.mentos.android.unicon.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageFragment() : BaseFragment<FragmentMypageBinding>() {
    private val myPageViewModel: MyPageViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_mypage

    override fun initDataBinding() {
        binding.lifecycleOwner = this;
        binding.viewModel = myPageViewModel

    }

    override fun initAfterBinding() {

    }
}