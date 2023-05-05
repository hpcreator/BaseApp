package com.skyline_info_system.baseapp.views.fragment

import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.databinding.FragmentPhotoPickerBinding
import com.skyline_info_system.baseapp.utils.loadImage
import com.skyline_info_system.baseapp.utils.setSafeClickListener
import com.skyline_info_system.baseapp.utils.showToast


class PhotoPickerFragment : BaseFragment() {

    private lateinit var binding: FragmentPhotoPickerBinding

    private val pickMultipleMedia =
        registerForActivityResult(PickMultipleVisualMedia(2)) { uris ->
            if (uris.isNotEmpty()) {
                Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
                binding.imgPhoto.loadImage(imageUrl = uris.last().toString(), cornerRadius = 20F, allowCaching = true,
                    onStart = {
                    },
                    onSuccess = { _, _ ->
                    },
                    onError = { _, _ ->
                    })
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    private val pickMedia = registerForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null) {
            val cR = requireContext().contentResolver
            if (cR.getType(uri) != null) {
                if (cR.getType(uri)!!.startsWith("image")) {
                    binding.imgPhoto.loadImage(imageUrl = uri.toString(), cornerRadius = 20F, allowCaching = true,
                        onStart = {
                        },
                        onSuccess = { _, _ ->
                        },
                        onError = { _, _ ->
                        })
                } else {
                    showToast("video selected")
                }
            }
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.fragment_photo_picker)
    }

    override fun initView() {
        binding = getBinding()

        binding.apply {
            btnSelectPhoto.setSafeClickListener {
                selectPhotoFromGallery()
            }

            btnSelectMultiplePhoto.setSafeClickListener {
                selectMultiplePhotoFromGallery()
            }
        }
    }


    private fun selectPhotoFromGallery() {
        pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageAndVideo))
    }

    private fun selectMultiplePhotoFromGallery() {
        pickMultipleMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
    }

}