package com.mentos.android.unicon.ui.main

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mentos.android.unicon.R
import com.mentos.android.unicon.databinding.ActivityMainBinding
import com.mentos.android.unicon.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mainViewModel: MainViewModel by viewModel()
    override fun getLayoutResId() = R.layout.activity_main
    private lateinit var navHostFragment: NavHostFragment

    override fun initDataBinding() {
        binding.lifecycleOwner = this;
        binding.viewModel = mainViewModel

        mainViewModel.navigationFlag.observe(this, { navigationFlag ->
            if (navigationFlag == 1)
                navHostFragment.navController.navigate(R.id.homeFragment)
        })
    }

    override fun initAfterBinding() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController: NavController = navHostFragment.findNavController()
        binding.mainBottomNavigation.setupWithNavController(navController)
    }
}