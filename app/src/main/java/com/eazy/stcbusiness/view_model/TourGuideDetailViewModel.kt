package com.eazy.stcbusiness.view_model

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.model.HappeningDetailModel
import com.eazy.stcbusiness.model.TourGuideDetailModel
import com.eazy.stcbusiness.network_module.ApiRepository
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnOnClickItemDetailListener : OnClickCallBackListener {
    fun onItemServiceClickListener()
    fun onItemDetailClickListener()
}

@HiltViewModel
class TourGuideDetailViewModel @Inject constructor(
    mContext: Application,
    private val apiRepository : ApiRepository
) : BaseViewModel<OnOnClickItemDetailListener>(){

    val mUrl = ObservableField<String>()
    private val mIsServiceTap = ObservableBoolean()

    fun getIsServiceTap() : ObservableBoolean {
        return mIsServiceTap
    }
    init {
        setTitle(mContext.getString(R.string.tour_guides))

        initView()

        setPriceTotal( 80.00, mContext)
    }

    private fun initView() {
        val mHappeningDetailModel = TourGuideDetailModel()
        mHappeningDetailModel.images = "https://dev.booknow.asia/images/home_slider_1.jpg"
        mUrl.set(mHappeningDetailModel.images)

        mPriceTotal.set(String.format("%s %s%s", " ", 8.00, "USD"))
    }

    fun onItemServiceClickListener() {
        mIsServiceTap.set(true)

        mView?.onItemServiceClickListener()
    }

    fun onItemDetailClickListener() {
        mIsServiceTap.set(false)

        mView?.onItemDetailClickListener()
    }
}