package com.skyline_info_system.baseapp.views.fragment

import android.os.Bundle
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.fragment_search)
    }

    override fun initView() {
        binding = getBinding()
    }
}