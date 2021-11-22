package com.mentos.android.unicon.ui.main.home

import com.mentos.android.unicon.R
import com.mentos.android.unicon.databinding.FragmentHomeBinding
import com.mentos.android.unicon.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment() : BaseFragment<FragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_home

    override fun initDataBinding() {
        binding.lifecycleOwner = this;
        binding.viewModel = homeViewModel


    }

    override fun initAfterBinding() {

    }
}