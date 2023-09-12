package com.eazy.stcbusiness.view_model

import android.app.Application
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.network_module.ApiRepository
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransportationBookingNowViewModel @Inject constructor(
    mContext: Application,
    private val apiRepository : ApiRepository
) : BaseViewModel<OnClickCallBackListener>() {

    val mTitle = ObservableField<String>()

    fun onClickDestination() {
        mView?.onClickCallBack()
    }

}