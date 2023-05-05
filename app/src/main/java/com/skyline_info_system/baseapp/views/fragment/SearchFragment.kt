package com.skyline_info_system.baseapp.views.fragment

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.databinding.FragmentSearchBinding
import com.skyline_info_system.baseapp.models.response.ComponentsModel
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
            findNavController().navigate(R.id.action_searchFragment_to_photoPickerFragment)
        }
        binding.adapter = componentsAdapter
        setupRecyclerview()
    }

    private fun setupRecyclerview() {
        componentsList.add(ComponentsModel(componentId = 1, name = "Photo Picker for Android 13"))
        componentsAdapter.addComponents(componentsList)
    }
}