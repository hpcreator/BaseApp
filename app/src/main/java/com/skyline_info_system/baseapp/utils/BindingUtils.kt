package com.skyline_info_system.baseapp.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skyline_info_system.baseapp.views.adapter.BaseViewHolder

@BindingAdapter("bind:item")
fun linearRecyclerAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<BaseViewHolder>?) {
    view.layoutManager = LinearLayoutManager(view.context)
    view.itemAnimator = DefaultItemAnimator()
    view.setHasFixedSize(false)
    view.adapter = adapter
}

@BindingAdapter("bind:itemHorizontal")
fun horizontalLinearRecyclerAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<BaseViewHolder>?) {
    view.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
    view.itemAnimator = DefaultItemAnimator()
    view.setHasFixedSize(false)
    view.adapter = adapter
}

@BindingAdapter("bind:twoColumnItem")
fun twoColumnRecyclerAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<BaseViewHolder>?) {
    view.layoutManager = GridLayoutManager(view.context, 2)
    view.itemAnimator = DefaultItemAnimator()
    view.setHasFixedSize(false)
    view.adapter = adapter
}

@BindingAdapter("bind:threeColumnItem")
fun threeColumnRecyclerAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<BaseViewHolder>?) {
    view.layoutManager = GridLayoutManager(view.context, 3)
    view.itemAnimator = DefaultItemAnimator()
    view.setHasFixedSize(false)
    view.adapter = adapter
}