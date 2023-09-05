package com.eazy.stcbusiness.base

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.eazy.stcbusiness.network_module.ErrorResponse

abstract class BaseActivity<VB : ViewDataBinding , VM: BaseViewModel<*>> : SampleBaseActivity(),
    BaseView {
    abstract val layoutId: Int
    abstract val mViewModel : VM
    protected lateinit var mBinding: VB

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, layoutId)
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

    override fun onError(errorResponse : ErrorResponse){

    }

}