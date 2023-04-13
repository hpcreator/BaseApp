package com.skyline_info_system.baseapp.views.fragment

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.views.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
/**
 * Common Fragment Class to handle all common functions which are use multiple times in fragments.
 * This is created with idea of code ReUsability in android
 */

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    private lateinit var mBinding: ViewDataBinding
    private var layoutId: Int = 0

    /**
     * Common Progress dialog to show loading process to user in entire app.
     */
    private val progressDialog: Dialog by lazy {
        Dialog(requireContext()).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.progress_dialog)
            val layoutParams = window?.attributes
            layoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT
            setCanceledOnTouchOutside(false)
            setCancelable(false)
            window?.attributes = layoutParams
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    abstract fun initView()
    private var baseActivity: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        initView()
        return mBinding.root
    }

    protected fun setView(layoutId: Int) {
        this.layoutId = layoutId
    }

    protected fun <T : ViewDataBinding> getBinding(): T {
        return mBinding as T
    }

    fun isInternetAvailable(): Boolean {
        return baseActivity?.isInternetAvailable!!
    }

    /**
     * Use to change Status bar color in different screens runtime.
     * pass true for @param[isTextColorWhite] will help to change text color light or dark according to color.
     */
    fun setStatusBar(color: Int, isTextColorWhite: Boolean = false) {
        baseActivity?.let {
            WindowCompat.getInsetsController(it.window, it.window.decorView).isAppearanceLightStatusBars = isTextColorWhite
            it.window.statusBarColor = color
        }
    }

    fun showProgressDialog() {
        if (!progressDialog.isShowing) {
            progressDialog.show()
        }
    }

    fun hideProgressDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    /**
     * Common Material Alert dialog to show alerts to user in entire app.
     */
    fun showAlertDialog(
        context: Context,
        icon: Drawable? = null,
        title: String,
        message: String,
        positiveButton: String,
        negativeButton: String? = null,
        onPositiveClick: (() -> Unit),
    ) {
        // you can ask param to set Icon centered and based on that create instance of MaterialAlert Dialog
        val materialDialog = MaterialAlertDialogBuilder(context, R.style.ThemeOverlay_App_MaterialAlertDialog)
        if (icon != null) {
            materialDialog.setIcon(icon)
        }
        materialDialog.setTitle(title)
        materialDialog.setMessage(message)
        materialDialog.setPositiveButton(positiveButton) { dialog, _ ->
            dialog.dismiss()
            onPositiveClick()
        }
        if (!negativeButton.isNullOrEmpty()) {
            materialDialog.setNegativeButton(negativeButton) { dialog, _ ->
                dialog.dismiss()
            }
        }
        materialDialog.setCancelable(false)
        materialDialog.show()
    }
}