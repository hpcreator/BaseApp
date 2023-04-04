package com.skyline_info_system.baseapp.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.google.android.material.snackbar.Snackbar
import com.skyline_info_system.baseapp.R

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.showToast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, length).show()
}

fun Fragment.showToast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    requireContext().showToast(msg, length)
}

fun View.showSnackBar(message: String) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    val textView: TextView = snackBar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.textSize = 12F
    textView.maxLines = 4
    snackBar.setAction(R.string.dismiss) {
        snackBar.dismiss()
    }
    snackBar.setActionTextColor(ContextCompat.getColor(context, R.color.md_theme_light_inversePrimary))
    snackBar.show()
}

fun View.setSafeClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun ImageView.loadImage(
    imageUrl: String = "",
    @DrawableRes image: Int? = null,
    @DrawableRes placeHolder: Int? = null,
    cornerRadius: Float = 0F,
    loadCircleCrop: Boolean = false,
    shouldCrossFade: Boolean = false,
    crossFadeDurationMillis: Int = 0,
    allowCaching: Boolean = false,
    onStart: ((request: ImageRequest) -> Unit),
    onSuccess: ((request: ImageRequest, result: SuccessResult) -> Unit),
    onError: ((request: ImageRequest, error: ErrorResult) -> Unit),
) {
    this.load(imageUrl.ifEmpty { image }) {
        diskCachePolicy(if (allowCaching) CachePolicy.ENABLED else CachePolicy.DISABLED)
        if (placeHolder != null) {
            this.placeholder(placeHolder)
        }
        if (cornerRadius > 0) {
            transformations(RoundedCornersTransformation(cornerRadius))
        }
        if (loadCircleCrop) {
            transformations(CircleCropTransformation())
        }
        if (shouldCrossFade) {
            crossfade(true)
            crossfade(crossFadeDurationMillis)
        }
        listener(onStart = onStart, onSuccess = onSuccess, onError = onError, onCancel = {
            Log.e("TAG", "loadImage: cancel")
        })
    }
}