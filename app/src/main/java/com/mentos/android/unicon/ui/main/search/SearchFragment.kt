package com.mentos.android.unicon.ui.main.search

import com.mentos.android.unicon.R
import com.mentos.android.unicon.databinding.FragmentSearchBinding
import com.mentos.android.unicon.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment() : BaseFragment<FragmentSearchBinding>() {
    private val searchViewModel: SearchViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_search

    override fun initDataBinding() {
        binding.lifecycleOwner = this;
        binding.viewModel = searchViewModel

    }

    override fun initAfterBinding() {

    }
}