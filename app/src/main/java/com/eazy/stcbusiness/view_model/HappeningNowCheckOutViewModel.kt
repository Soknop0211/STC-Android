package com.eazy.stcbusiness.view_model

import android.app.Application
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HappeningNowCheckOutViewModel @Inject constructor(private val mContext: Application) : BaseViewModel<BaseView>(){
    init {
        setTitle("Checkout")
    }
}