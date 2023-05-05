package com.skyline_info_system.baseapp.views.activity

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.databinding.ActivityMainBinding
import com.skyline_info_system.baseapp.network.internet.NetworkViewModel
import com.skyline_info_system.baseapp.utils.addDelay
import com.skyline_info_system.baseapp.utils.gone
import com.skyline_info_system.baseapp.utils.logError
import com.skyline_info_system.baseapp.utils.visible
import com.skyline_info_system.baseapp.views.fragment.VideoFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var mCurrentFragment: Fragment? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val mViewModel: NetworkViewModel by viewModels()
    var isInternetAvailable: Boolean? = null

    private val onBackInvokeCallBack = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            mCurrentFragment = getCurrentFragment()
            if (mCurrentFragment !is VideoFragment) {
                //binding.bottomNavigationView.selectedItemId = R.id.videoFragment
                navController.popBackStack()
            } else {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this, onBackInvokeCallBack)

        binding.apply {
            navController =
                (supportFragmentManager.findFragmentById(fragmentContainer.id) as NavHostFragment).findNavController()
            bottomNavigationView.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.videoPlayerFragment -> bottomNavigationView.gone()
                    else -> bottomNavigationView.visible()
                }
            }
        }


        lifecycleScope.launch {
            mViewModel.isConnected.collectLatest {
                isInternetAvailable = it
                logError(msg = "network connected: $it")
                binding.networkAvailable = it
                if (it) {
                    binding.tvInternetStatus.visible()
                    addDelay(1500) {
                        binding.tvInternetStatus.gone()
                    }
                } else {
                    binding.tvInternetStatus.visible()
                }
            }
        }
    }

    private fun getCurrentFragment(): Fragment? {
        val navHostFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }
}