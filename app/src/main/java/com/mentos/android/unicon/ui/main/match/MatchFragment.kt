package com.mentos.android.unicon.ui.main.match

import com.mentos.android.unicon.R
import com.mentos.android.unicon.databinding.FragmentMatchBinding
import com.mentos.android.unicon.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchFragment() : BaseFragment<FragmentMatchBinding>() {
    private val matchingViewModel: MatchViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_match

    override fun initDataBinding() {
        binding.lifecycleOwner = this;
        binding.viewModel = matchingViewModel

    }

    override fun initAfterBinding() {

    }
}