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
    fun onClickSchedule()
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

    val mTitle = ObservableField<String>()


    // Book Schedule
    val mIsShowSchedule = ObservableBoolean(false)

    val mScheduleTxt = ObservableField<String>()

    fun onClickSchedule() {
        mView?.onClickSchedule()
    }


    init {
        mAddressDestination.set(mContext.getString(R.string.where_to))
        mScheduleTxt.set(mContext.getString(R.string.when_trip))
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