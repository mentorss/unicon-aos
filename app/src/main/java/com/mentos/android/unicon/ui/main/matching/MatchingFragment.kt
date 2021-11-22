package com.mentos.android.unicon.ui.main.matching

import com.mentos.android.unicon.R
import com.mentos.android.unicon.databinding.FragmentMatchingBinding
import com.mentos.android.unicon.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchingFragment() : BaseFragment<FragmentMatchingBinding>() {
    private val matchingViewModel: MatchingViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_matching

    override fun initDataBinding() {
        binding.lifecycleOwner = this;
        binding.viewModel = matchingViewModel

    }

    override fun initAfterBinding() {

    }
}