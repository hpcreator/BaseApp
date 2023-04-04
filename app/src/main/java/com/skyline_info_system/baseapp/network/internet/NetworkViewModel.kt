package com.skyline_info_system.baseapp.network.internet

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(networkListener: NetworkListener) : ViewModel() {
    val isConnected: Flow<Boolean> = networkListener.isConnected
}