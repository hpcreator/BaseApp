package com.skyline_info_system.baseapp.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.databinding.ItemCarouselBinding
import com.skyline_info_system.baseapp.models.response.CarouselModel
import com.skyline_info_system.baseapp.utils.loadImage
import com.skyline_info_system.baseapp.utils.setSafeClickListener

class CarouselAdapter(
    val onItemClick: ((component: CarouselModel) -> Unit),
) : RecyclerView.Adapter<BaseViewHolder>() {
    private var componentList: ArrayList<CarouselModel> = arrayListOf()
    lateinit var binding: ItemCarouselBinding

    inner class ComponentViewHolder(itemView: View) : BaseViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ComponentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        binding = holder.getBinding()
        with(holder) {
            binding.apply {
                with(componentList[position]) {
                    carouselImageView.loadImage(imageUrl = image, cornerRadius = 20F, allowCaching = true,
                        onStart = {
                        },
                        onSuccess = { _, _ ->
                        },
                        onError = { _, _ ->
                        })
                    root.setSafeClickListener { onItemClick(componentList[bindingAdapterPosition]) }
                }
            }
        }
    }

    override fun getItemCount() = componentList.size

    fun addComponents(list: ArrayList<CarouselModel>) {
        if (componentList.isNotEmpty()) componentList.clear()
        componentList.addAll(list)
        notifyDataSetChanged()
    }
}