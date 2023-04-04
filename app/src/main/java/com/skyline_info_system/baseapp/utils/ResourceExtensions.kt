package com.skyline_info_system.baseapp.utils

import android.content.Context
import android.view.animation.AnimationUtils
import androidx.annotation.*
import androidx.core.content.res.ResourcesCompat


fun Context.color(@ColorRes colorId: Int) = ResourcesCompat.getColor(resources, colorId, null)

fun Context.drawable(@DrawableRes resId: Int) = ResourcesCompat.getDrawable(resources, resId, null)

fun Context.font(@FontRes resId: Int) = ResourcesCompat.getFont(this, resId)

fun Context.dimen(@DimenRes resId: Int) = resources.getDimension(resId)

fun Context.animation(@AnimRes resId: Int) = AnimationUtils.loadAnimation(this, resId)

fun Context.text(@StringRes resId: Int? = null, string: String = "") = if (resId != null) getString(resId) else string