package com.skyline_info_system.baseapp.views.fragment

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.databinding.FragmentProfileBinding
import com.skyline_info_system.baseapp.utils.setSafeClickListener

class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.fragment_profile)
    }

    override fun initView() {
        binding = getBinding()

        binding.imgWhatsApp.setSafeClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_whatsAppMessageFragment)
        }
    }
}