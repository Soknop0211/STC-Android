package com.eazy.stcbusiness.base

import android.view.View
import com.eazy.stcbusiness.network_module.ErrorResponse

interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun showKeyboard()
    fun showKeyboard(view: View)
    fun hideKeyboard()
    fun onError(errorResponse : ErrorResponse)
}