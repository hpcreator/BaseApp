package com.skyline_info_system.baseapp.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skyline_info_system.baseapp.databinding.ItemVideoLayoutBinding
import com.skyline_info_system.baseapp.models.response.VideoListResponse
import com.skyline_info_system.baseapp.utils.loadImage
import com.skyline_info_system.baseapp.utils.setSafeClickListener
import com.skyline_info_system.baseapp.utils.text

class VideoAdapter(
    val context: Context,
    private var videoList: ArrayList<VideoListResponse.Hit>,
    val onItemClick: ((video: VideoListResponse.Hit) -> Unit),
) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    inner class VideoViewHolder(val binding: ItemVideoLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(ItemVideoLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount() = videoList.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
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