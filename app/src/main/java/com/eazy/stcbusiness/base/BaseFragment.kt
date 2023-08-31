package com.eazy.stcbusiness.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.eazy.stcbusiness.network_module.ErrorResponse
import java.util.UUID

abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel<*>> : SampleBaseFragment(), BaseView {

    val fragmentTag = UUID.randomUUID().toString()
    open var backStackCallback: (tag: String) -> Unit = {}
    abstract val layoutResource: Int
    abstract val mViewModel : VM
    protected lateinit var mBinding: T

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        return mBinding.root
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