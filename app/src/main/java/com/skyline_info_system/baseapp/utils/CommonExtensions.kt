package com.skyline_info_system.baseapp.utils

import android.os.Handler
import android.os.Looper

fun addDelay(timeInMillis: Long, runBlock: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(Runnable(runBlock), timeInMillis)
}