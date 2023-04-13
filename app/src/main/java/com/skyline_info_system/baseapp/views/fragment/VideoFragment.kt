package com.skyline_info_system.baseapp.views.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.databinding.FragmentVideoBinding
import com.skyline_info_system.baseapp.models.response.VideoListResponse
import com.skyline_info_system.baseapp.network.ApiResponse
import com.skyline_info_system.baseapp.utils.showSnackBar
import com.skyline_info_system.baseapp.viewmodel.UserViewModel
import com.skyline_info_system.baseapp.views.adapter.VideoAdapter
import kotlinx.coroutines.launch

class VideoFragment : BaseFragment() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var videoList: ArrayList<VideoListResponse.Hit>

    private lateinit var binding: FragmentVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.fragment_video)
    }

    override fun initView() {
        binding = getBinding()
        setupRecyclerview()
        binding.apply {
            if (isInternetAvailable()) getVideoList()
        }
    }

    private fun setupRecyclerview() {
        videoList = arrayListOf()
        videoAdapter = VideoAdapter(requireContext(), videoList) { _ ->
            findNavController().navigate(R.id.action_videoFragment_to_videoPlayerFragment)
        }
        binding.rvVideos.adapter = videoAdapter
    }

    private fun getVideoList() {
        lifecycleScope.launch {
            userViewModel.getVideosList().observe(this@VideoFragment) {
                when (it) {
                    is ApiResponse.Success -> {
                        hideProgressDialog()
                        videoList = it.data!!
                        videoAdapter.addVideos(videoList)
                    }
                    is ApiResponse.SessionExpire -> {
                        hideProgressDialog()
                    }
                    is ApiResponse.Error -> {
                        hideProgressDialog()
                        binding.root.showSnackBar(it.message.toString())
                    }
                    is ApiResponse.Loading -> {
                        showProgressDialog()
                    }
                    else -> {
                        hideProgressDialog()
                        binding.root.showSnackBar(it?.message.toString())
                    }
                }
            }
        }
    }
}