package com.eazy.stcbusiness.view_model

import android.app.Application
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.model.HappeningDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnClickConfirmListener : BaseView {
    fun onClickCallBack()
}

@HiltViewModel
class HappeningNowDetailViewModel @Inject constructor(private val mContext: Application) : BaseViewModel<OnClickConfirmListener>(){

    val mUrl = ObservableField<String>()

    override fun onClickBookNow() {
        mView?.onClickCallBack()
    }

    init {
      initView()
    }

    private fun initView() {
        val mHappeningDetailModel = HappeningDetailModel()
        mHappeningDetailModel.images = "https://dev.booknow.asia/images/home_slider_1.jpg"
        mUrl.set(mHappeningDetailModel.images)

        mPriceTotal.set(String.format("%s %s%s", " ", 8.00, "USD"))
    }


}