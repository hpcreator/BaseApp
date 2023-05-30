package com.skyline_info_system.baseapp.views.fragment

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.databinding.FragmentSearchBinding
import com.skyline_info_system.baseapp.models.response.ComponentsModel
import com.skyline_info_system.baseapp.utils.showToast
import com.skyline_info_system.baseapp.views.adapter.ComponentsAdapter

class SearchFragment : BaseFragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var componentsAdapter: ComponentsAdapter
    private lateinit var componentsList: ArrayList<ComponentsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.fragment_search)
    }

    override fun initView() {
        binding = getBinding()
        componentsList = arrayListOf()
        componentsAdapter = ComponentsAdapter {
            when (it.componentId) {
                1 -> {
                    findNavController().navigate(R.id.action_searchFragment_to_photoPickerFragment)
                }

                2 -> {
                    openRecorderApp()
                }

                3 -> {
                    findNavController().navigate(R.id.action_searchFragment_to_carouselFragment)
                }

                else -> {

                }
            }
        }
        binding.adapter = componentsAdapter
        setupRecyclerview()
    }

    private fun setupRecyclerview() {
        componentsList.add(ComponentsModel(componentId = 1, name = "Photo Picker for Android 13"))
        componentsList.add(ComponentsModel(componentId = 2, name = "Open Another App with data"))
        componentsList.add(ComponentsModel(componentId = 3, name = "Carousel View"))
        componentsAdapter.addComponents(componentsList)
    }


    private fun openRecorderApp() {
        val receiverAppPackageName = "com.skyline_info_system.recordme"
        val isAppInstalled = appInstalledOrNot(receiverAppPackageName)
        if (isAppInstalled) {
            val myAction: Uri =
                Uri.parse("https://$receiverAppPackageName?appName=${getString(R.string.app_name)}")// need to change this to nutrition
            val packageManager = requireActivity().packageManager
            val intent = packageManager.getLaunchIntentForPackage(receiverAppPackageName)
            if (intent != null) {
                intent.data = myAction
                startActivity(intent)
            }
        } else {
            showToast("Application is not installed with packageName: $receiverAppPackageName")
        }
    }

    private fun appInstalledOrNot(packageName: String): Boolean {
        val pm = requireActivity().packageManager
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                pm.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(PackageManager.GET_ACTIVITIES.toLong()))
            } else {
                @Suppress("DEPRECATION") pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            }
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return false
    }
}