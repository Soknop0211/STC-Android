package com.eazy.stcbusiness.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.eazy.stcbusiness.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class SampleBaseBottomSheetDialogFragment<T : ViewDataBinding> : BottomSheetDialogFragment() {

    var mActivity: Activity? = null
    protected lateinit var mBinding: T
    abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.CustomBottomSheetDialogFragment)
    }

    fun setKeyboardWithEditText() {
        if(dialog != null &&  dialog?.window != null) {
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        return mBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (mActivity == null && context is SampleBaseActivity) {
            mActivity = context
        }
    }

}