package com.skyline_info_system.baseapp.utils

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import com.skyline_info_system.baseapp.BuildConfig

fun Context.logError(msg: String?) {
    msg?.run {
        if (BuildConfig.DEBUG) {
            Log.e(this@logError.javaClass.simpleName, msg)
        }
    }
}

fun Fragment.logError(msg: String?) {
    requireContext().logError(msg)
}

fun Context.logInfo(msg: String?) {
    msg?.run {
        if (BuildConfig.DEBUG) {
            Log.i(this@logInfo.javaClass.simpleName, msg)
        }
    }
}

fun Fragment.logInfo(msg: String?) {
    requireContext().logError(msg)
}