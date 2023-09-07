package com.eazy.stcbusiness.view_model

import android.app.Application
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.base.SampleBaseActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnClickListener : BaseView{
    fun onClickListener()
}

@HiltViewModel
class DestinationLocationViewModel @Inject constructor(mContext: Application) : BaseViewModel<OnClickListener>(){

    override fun onClickBookNow() {
        mView?.onClickListener()
    }

    init {
        setPriceTotal(8.00, mContext)
    }

    fun onClickApply() {
        mView?.onClickListener()
    }

    fun onClickReset() {
        mView?.onClickListener()
    }

}