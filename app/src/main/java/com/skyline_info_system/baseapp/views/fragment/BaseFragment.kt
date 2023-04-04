package com.skyline_info_system.baseapp.views.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.skyline_info_system.baseapp.R
import com.skyline_info_system.baseapp.views.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    private lateinit var mBinding: ViewDataBinding
    private var layoutId: Int = 0

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
}