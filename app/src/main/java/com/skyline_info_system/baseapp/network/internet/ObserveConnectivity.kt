package com.skyline_info_system.baseapp.network.internet

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log

object ObserveConnectivity {
    private val IMPL = AboveMarshMellowImpl

    fun isConnected(manager: ConnectivityManager): Boolean {
        Log.e("TAG", "isConnected: ${IMPL.isConnected(manager)}")
        return IMPL.isConnected(manager)
    }
}

interface ConnectivityCompat {
    fun isConnected(manager: ConnectivityManager): Boolean
}

object AboveMarshMellowImpl : ConnectivityCompat {
    override fun isConnected(manager: ConnectivityManager): Boolean {
        return manager.getNetworkCapabilities(manager.activeNetwork)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}