package com.skyline_info_system.baseapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.skyline_info_system.baseapp.network.ErrorModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import java.io.File
import java.util.Locale

fun Context.isNetworkAvailable(): Boolean {
    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = manager.getNetworkCapabilities(manager.activeNetwork)
    return if (capabilities != null) {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_WIFI
        ) || capabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_ETHERNET
        )
    } else false
}

fun Fragment.isNetworkAvailable() = requireContext().isNetworkAvailable()

object NetworkExtensions {

    fun getErrorMessage(responseBody: ResponseBody?): ErrorModel? {
        try {
            responseBody?.let {
                val json = JsonParser.parseString(it.toString())
                return Gson().fromJson(json, ErrorModel::class.java)
            } ?: let {
                return null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun File?.toMultipartBody(name: String): MultipartBody.Part? {
        this ?: return null
        return MultipartBody.Part.createFormData(
            name, this.name, this.asRequestBody(getMimeType().toMediaTypeOrNull())
        )
    }

    private fun File.getMimeType(): String {
        val url = this.toURI().toURL().toString()
        val extension = url.substringAfterLast(".")
        return when (extension.lowercase(Locale.getDefault())) {
            "jpg", "jpeg", "png" -> "image/$extension"
            "mp4" -> "video/$extension"
            "pdf" -> "application/$extension"
            else -> "image/*"
        }
    }

    fun createPartFromString(string: String): RequestBody {
        return string.toRequestBody(MultipartBody.FORM)
    }
}