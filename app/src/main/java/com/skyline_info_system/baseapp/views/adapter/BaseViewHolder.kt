package com.skyline_info_system.baseapp.views.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var binding: ViewDataBinding

    init {
        setBinding(itemView)
    }

    fun <T : ViewDataBinding> getBinding(): T {
        return binding as T
    }

    private fun setBinding(itemView: View) {
        binding = DataBindingUtil.bind(itemView)!!
    }
}