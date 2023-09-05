package com.eazy.stcbusiness.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.network_module.ErrorResponse
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<T : ViewDataBinding, VM : BaseViewModel<*>> : BottomSheetDialogFragment(), BaseView {

    abstract val layoutResource: Int
    abstract val mViewModel : VM
    protected lateinit var mBinding: T

    var mActivity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.CustomBottomSheetDialogFragment)
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

    open fun setVariable(id: Int, value: Any) {
        mBinding.setVariable(id, value)
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showKeyboard() {

    }

    override fun showKeyboard(view: View) {

    }

    override fun hideKeyboard() {

    }

    override fun onError(errorResponse: ErrorResponse) {

    }
}