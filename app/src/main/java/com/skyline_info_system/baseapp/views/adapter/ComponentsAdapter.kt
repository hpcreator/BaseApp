package com.skyline_info_system.baseapp.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.databinding.ItemSearchComponentsBinding
import com.skyline_info_system.baseapp.models.response.ComponentsModel
import com.skyline_info_system.baseapp.utils.setSafeClickListener

class ComponentsAdapter(
    val onItemClick: ((component: ComponentsModel) -> Unit),
) : RecyclerView.Adapter<BaseViewHolder>() {
    private var componentList: ArrayList<ComponentsModel> = arrayListOf()
    lateinit var binding: ItemSearchComponentsBinding

    inner class ComponentViewHolder(itemView: View) : BaseViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ComponentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_components, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        binding = holder.getBinding()
        with(holder) {
            binding.apply {
                with(componentList[position]) {
                    componentName = name
                    root.setSafeClickListener { onItemClick(componentList[bindingAdapterPosition]) }
                }
            }
        }
    }

    override fun getItemCount() = componentList.size

    fun addComponents(list: ArrayList<ComponentsModel>) {
        if (componentList.isNotEmpty()) componentList.clear()
        componentList.addAll(list)
        notifyDataSetChanged()
    }
}