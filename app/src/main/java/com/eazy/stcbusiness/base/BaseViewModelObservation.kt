package com.eazy.stcbusiness.base

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

open class BaseViewModelObservation : ViewModel() {

    val mIsLoading: ObservableBoolean = ObservableBoolean(false)

    val mTitleToolbar = ObservableField<String>()

    fun setLoading(isLoading : Boolean) {
        mIsLoading.set(isLoading)
    }

    fun setTitle(mTitle : String) {
        mTitleToolbar.set(mTitle)
    }

//    fun onBackPressed() {
//        if (context is SampleBaseActivity) {
//            context.finish()
//        }
//    }
}