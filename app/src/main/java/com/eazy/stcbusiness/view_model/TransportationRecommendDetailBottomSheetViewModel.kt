package com.eazy.stcbusiness.view_model

import android.app.Application
import android.content.res.Resources
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.model.CarRentalRecommendModel
import com.eazy.stcbusiness.model.SavePlaceModel
import com.eazy.stcbusiness.utils.FormatPriceHelper
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnClickRecommendListener : OnClickCallBackListener {
    fun onDismiss()
}

@HiltViewModel
class TransportationRecommendDetailBottomSheetViewModel  @Inject constructor(
    private val mContext: Application) : BaseViewModel<OnClickRecommendListener>(){

    private val mTitle = ObservableField<String>()

    private val mDescription = ObservableField<String>()

    private val mPrice = ObservableField<String>()

    fun getTitle() : ObservableField<String> {
        return mTitle
    }

    fun getPrice() : ObservableField<String> {
        return mPrice
    }

    fun getDescription() : ObservableField<String> {
        return mDescription
    }

    fun initData(mData : CarRentalRecommendModel) {
        mTitle.set(mData.name)
        mDescription.set(mData.description)
        mPrice.set(String.format("%s %s", FormatPriceHelper.getDisplayPrice(mContext, "USD", mData.price ?: 0.0), "/"))
        mTitle.set(mData.name)

        // Base Bottom Price
        setIsShowPriceOnly(true)
        mPriceTotal.set(String.format("%s", FormatPriceHelper.getDisplayPrice(mContext, "USD", mData.price ?: 0.0)))
    }

    private val _itemList = MutableLiveData<ArrayList<String>>()
    val itemList: LiveData<ArrayList<String>> get() = _itemList

    fun initListFeature() {
        val mListSubList = arrayListOf(" Airbag", "FM Radio", "Power Windows", "Sensor", "Speed Km", "Steering Wheel")
        _itemList.value = mListSubList

    }


    override fun onClickBookNow() {
        mView?.onClickCallBack()
    }

}