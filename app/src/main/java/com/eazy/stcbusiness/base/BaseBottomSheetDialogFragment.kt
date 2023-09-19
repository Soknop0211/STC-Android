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
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.network_module.ErrorResponse
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<T : ViewDataBinding, VM : BaseViewModel<*>> : BottomSheetDialogFragment(), BaseView {

    abstract val layoutResource: Int
    abstract val mViewModel : VM
    protected lateinit var mBinding: T

    var mActivity: Activity? = null

    fun setKeyboardWithEditText() {
        if(dialog != null &&  dialog?.window != null) {
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }

    }

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

    open fun getArgumentsString(key: String, arguments: Bundle?) : String{
        if (arguments != null && arguments.containsKey(key)) {
            return arguments.getString(key).toString()
        }
        return ""
    }

    open fun <T> getDataFromModelClass(key: String, arguments: Bundle?) : T? {
        if (arguments != null && arguments.containsKey(key)) {
            return arguments.getSerializable(key) as T
        }
        return null
    }

    open fun <T> getArgumentsList(key: String, arguments: Bundle?): ArrayList<T> {
        if (arguments != null && arguments.containsKey(key)) {
            return arguments.getSerializable(key) as ArrayList<T>
        }
        return ArrayList()
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