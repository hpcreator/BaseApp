package com.skyline_info_system.baseapp.repository

import com.skyline_info_system.baseapp.db.dao.UserDao
import com.skyline_info_system.baseapp.models.response.VideoListResponse
import com.skyline_info_system.baseapp.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
) {

    suspend fun getVideos(pageSize: Int, urlRequire: Boolean): Response<VideoListResponse> =
        apiService.getVideos(pageSize, urlRequire)


}