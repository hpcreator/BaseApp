package com.skyline_info_system.baseapp.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.databinding.ItemVideoLayoutBinding
import com.skyline_info_system.baseapp.models.response.VideoListResponse
import com.skyline_info_system.baseapp.utils.loadImage
import com.skyline_info_system.baseapp.utils.setSafeClickListener
import com.skyline_info_system.baseapp.utils.text

class VideoAdapter(
    val context: Context,
    private var videoList: ArrayList<VideoListResponse.Hit>,
    val onItemClick: ((video: VideoListResponse.Hit) -> Unit),
) : RecyclerView.Adapter<BaseViewHolder>() {
    inner class VideoViewHolder(itemView: View) : BaseViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        //return VideoViewHolder(ItemVideoLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
        return VideoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_video_layout, parent, false))
    }

    override fun getItemCount() = videoList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val binding: ItemVideoLayoutBinding = holder.getBinding()
        with(holder) {
            binding.apply {
                with(videoList[position]) {
                    tvVideoTitle.text = context.text(string = title.toString())
                    tvVideoDesc.text = context.text(string = description.toString())
                    imgVideo.loadImage(imageUrl = poster.toString(), allowCaching = true,
                        onStart = {
                            progressCircular.show()
                        },
                        onSuccess = { _, _ ->
                            progressCircular.hide()
                        },
                        onError = { _, _ ->
                            progressCircular.hide()
                        })
                    root.setSafeClickListener { onItemClick(videoList[bindingAdapterPosition]) }
                }
            }
        }
    }

    fun addVideos(list: ArrayList<VideoListResponse.Hit>) {
        videoList.clear()
        videoList.addAll(list)
        notifyDataSetChanged()
    }
}