package com.skyline_info_system.baseapp.network

import com.skyline_info_system.baseapp.models.response.VideoListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("videos")
    suspend fun getVideos(
        @Query("page_size") pageSize: Int,
        @Query("urls") urlRequires: Boolean,
    ): Response<VideoListResponse>
}