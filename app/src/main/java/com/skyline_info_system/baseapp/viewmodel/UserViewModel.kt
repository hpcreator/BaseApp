package com.skyline_info_system.baseapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.skyline_info_system.baseapp.network.ApiResponse
import com.skyline_info_system.baseapp.network.ErrorModel
import com.skyline_info_system.baseapp.repository.MainRepository
import com.skyline_info_system.baseapp.utils.Constants.ApiStatusCode.SESSION_EXPIRE
import com.skyline_info_system.baseapp.utils.Constants.ApiStatusCode.STATUS_OK
import com.skyline_info_system.baseapp.utils.NetworkExtensions.getErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {


    fun getVideosList(pageSize: Int = 20, urlRequires: Boolean = true) = liveData(Dispatchers.IO) {
        emit(ApiResponse.Loading(null))
        try {
            val apiResponse = mainRepository.getVideos(pageSize, urlRequires)
            when {
                apiResponse.code() == STATUS_OK -> {
                    emit(ApiResponse.Success(apiResponse.body()?.hits))
                }
                apiResponse.code() == SESSION_EXPIRE -> {
                    val errorModel: ErrorModel? = getErrorMessage(apiResponse.errorBody())
                    emit(ApiResponse.SessionExpire(null, errorModel?.message.toString()))
                }
                else -> {
                    val errorModel: ErrorModel? = getErrorMessage(apiResponse.errorBody())
                    emit(ApiResponse.Error(null, errorModel?.message.toString()))
                }
            }

        } catch (exception: Exception) {
            emit(exception.localizedMessage?.let { ApiResponse.Error(null, it) })
        }
    }

}