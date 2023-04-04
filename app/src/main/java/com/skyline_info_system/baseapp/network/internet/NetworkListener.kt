package com.skyline_info_system.baseapp.network.internet

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NetworkListener @Inject constructor(connectivityManager: ConnectivityManager) {

    val isConnected = callbackFlow {
        val callBack = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(false)
            }
        }

        val request = NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).apply {
            addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        }.addTransportType(NetworkCapabilities.TRANSPORT_WIFI).addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        trySend(ObserveConnectivity.isConnected(connectivityManager))
        connectivityManager.registerNetworkCallback(request, callBack)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callBack)
        }
    }
}