package com.eazy.stcbusiness.view_model

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.network_module.ApiRepository
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnClickBackListener : OnClickCallBackListener {
    fun onClickCurrentLocation()
    fun onClickConfirmButton(isDestinationAction : Boolean)
}

@HiltViewModel
class TransportationBookingNowViewModel @Inject constructor(
    mContext: Application,
    private val apiRepository : ApiRepository
) : BaseViewModel<OnClickBackListener>() {

    val mLatPickUp = ObservableField<Double>()
    val mLongPickUP = ObservableField<Double>()
    val mAddressPickUp = ObservableField<String>()

    val mLatDestination = ObservableField<Double>()
    val mLongDestination = ObservableField<Double>()
    val mAddressDestination = ObservableField<String>()
    val mIsDestinationAction = ObservableBoolean(true)
    val mValidButton = ObservableBoolean(false)

    init {
        mAddressDestination.set(mContext.getString(R.string.where_to))
    }

    fun getIsDestinationAction() : ObservableBoolean {
        return mIsDestinationAction
    }

    fun setIsDestinationAction(isDestinationAction : Boolean) {
        mIsDestinationAction.set(isDestinationAction)
    }

    fun onClickDestination() {
        mView?.onClickCallBack()
    }

    fun onClickCurrentLocation() {
        mView?.onClickCurrentLocation()
    }

    fun onClickConfirmButton() {
        mView?.onClickConfirmButton(mIsDestinationAction.get())
    }

}