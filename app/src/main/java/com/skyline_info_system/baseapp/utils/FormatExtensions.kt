package com.skyline_info_system.baseapp.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

fun Long.getRelativeTime(hoursRequire: Boolean = false): String {
    val hour = TimeUnit.MILLISECONDS.toHours(this) % 60
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(this) % 60
    return if (hoursRequire) {
        String.format("%02d:%02d:%02d", hour, minutes, seconds)
    } else {
        String.format("%02d:%02d", minutes, seconds)
    }
}

fun convertToUTC() {

}

@Suppress("DEPRECATION")
fun String.localToUTC(): Date {
    val date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDateTime.parse(this)
    } else {
        Date.parse(this)
    }
    val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC/GMT")
    return Date(sdf.format(date))
}

fun String.toDate(format: String = "yyyy-MM-dd HH:mm:ss"): Date? {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.parse(this)
}

fun Date.toStringFormat(format: String = "yyyy-MM-dd HH:mm:ss"): String {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(this)
}