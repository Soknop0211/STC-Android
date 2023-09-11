package com.eazy.stcbusiness.base

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.eazy.stcbusiness.utils.getDisplayPrice

open class BaseViewModelObservation : ViewModel() {

    val mIsLoading: ObservableBoolean = ObservableBoolean(false)

    val mTitleToolbar = ObservableField<String>()

    var mGetContext: Context? = null

    fun setLoading(isLoading : Boolean) {
        mIsLoading.set(isLoading)
    }

    fun setTitle(mTitle : String) {
        mTitleToolbar.set(mTitle)
    }

    val mPriceTotal = ObservableField<String>()

    open fun setPriceTotal(mPrice : Double, mContext : Context) {
        mPriceTotal.set(mContext.getDisplayPrice("USD", mPrice).plus(" / "))
    }

    fun setContext(context: Context) {
        mGetContext = context
    }

    open fun onClickBookNow() { }

    open fun onDismissButton() {
        if (mGetContext is SampleBaseActivity) {
            (mGetContext as SampleBaseActivity).finish()
        }
    }

}