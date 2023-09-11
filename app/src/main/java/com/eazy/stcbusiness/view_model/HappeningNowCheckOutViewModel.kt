package com.eazy.stcbusiness.view_model

import android.app.Application
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HappeningNowCheckOutViewModel @Inject constructor(private val mContext: Application) : BaseViewModel<OnClickCallBackListener>(){

    val mUrl = ObservableField<String>()

    init {
        setTitle("Checkout")

        mUrl.set("https://dev.booknow.asia/images/home_slider_1.jpg")

        mPriceTotal.set(String.format("%s %s%s", " ", 8.00, "USD"))
    }

    override fun onClickBookNow() {
        mView?.onClickCallBack()
    }
}