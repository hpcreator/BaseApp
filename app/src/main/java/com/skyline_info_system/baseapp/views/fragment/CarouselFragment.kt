package com.skyline_info_system.baseapp.views.fragment

import android.os.Bundle
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.databinding.FragmentCarouselBinding
import com.skyline_info_system.baseapp.models.response.CarouselModel
import com.skyline_info_system.baseapp.views.adapter.CarouselAdapter

class CarouselFragment : BaseFragment() {

    private lateinit var binding: FragmentCarouselBinding
    private lateinit var componentsAdapter: CarouselAdapter
    private lateinit var componentsList: ArrayList<CarouselModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.fragment_carousel)
    }

    override fun initView() {
        binding = getBinding()
        componentsList = arrayListOf()
        componentsAdapter = CarouselAdapter {
        }
        binding.carouselRecyclerView.adapter = componentsAdapter
        setupRecyclerview()
    }

    private fun setupRecyclerview() {
        componentsList.add(CarouselModel(image = "https://receipts.greentill.co/greentill/production/admin/slider/1d47a23f-5d5d-410f-bd30-0d365324a408.png"))
        componentsList.add(CarouselModel(image = "https://receipts.greentill.co/greentill/production/admin/slider/841e56f3-b288-40ff-a690-1370fe7f5a5d.png"))
        componentsList.add(CarouselModel(image = "https://receipts.greentill.co/greentill/production/admin/slider/f815a1c4-307c-49ab-9ec3-f71241615ed4.png"))
        componentsList.add(CarouselModel(image = "https://receipts.greentill.co/greentill/production/admin/slider/89a84989-cc82-4f4a-9360-ab5ab81f73a9.png"))
        componentsAdapter.addComponents(componentsList)
    }
}