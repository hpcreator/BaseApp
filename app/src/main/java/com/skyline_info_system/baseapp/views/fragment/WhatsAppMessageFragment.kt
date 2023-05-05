package com.skyline_info_system.baseapp.views.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.databinding.FragmentWhatsAppMessageBinding
import com.skyline_info_system.baseapp.utils.setSafeClickListener
import java.net.URLEncoder

class WhatsAppMessageFragment : BaseFragment() {
    private lateinit var binding: FragmentWhatsAppMessageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.fragment_whats_app_message)
    }

    override fun initView() {
        binding = getBinding()

        binding.apply {
            btnSend.setSafeClickListener {
                sendMessageToWhatsapp(code = "+91", phoneNumber = "7046937049", message = "Hello dear...")
            }
        }
    }


    fun sendMessageToWhatsapp(code: String, phoneNumber: String, message: String) {
        val url = "https://api.whatsapp.com/send?phone=$code$phoneNumber&text=${URLEncoder.encode(message, "UTF-8")}"
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setPackage("com.whatsapp")
            data = Uri.parse(url)
        }
        intent.resolveActivity(requireContext().packageManager)?.let {
            startActivity(intent)
        }
    }
}