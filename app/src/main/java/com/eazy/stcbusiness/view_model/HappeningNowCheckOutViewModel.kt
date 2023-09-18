package com.eazy.stcbusiness.view_model

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.databinding.SuggestedRideCarRentalLayoutBinding
import com.eazy.stcbusiness.model.CarRentalRecommendModel
import com.eazy.stcbusiness.model.CarRentalSuggestedRideModel
import com.eazy.stcbusiness.utils.FormatPriceHelper
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnCheckOutCallBackListener : OnClickCallBackListener {
    fun onAddPeople()
    fun onAddNot()
}

@HiltViewModel
class HappeningNowCheckOutViewModel @Inject constructor(private val mContext: Application) : BaseViewModel<OnCheckOutCallBackListener>(){

    val mUrl = ObservableField<String>()
    private val mIsCarRental = ObservableBoolean(true)

    init {
        setTitle("Checkout")

        mUrl.set("https://dev.booknow.asia/images/home_slider_1.jpg")

        mPriceTotal.set(String.format("%s %s%s", " ", 8.00, "USD"))
    }

    override fun onClickBookNow() {
        mView?.onClickCallBack()
    }

    fun onAddPeople() {
        mView?.onAddPeople()
    }

    // Transportation
    private var mNoteAdded = ObservableField<String>()

    fun getNoteAdded() : ObservableField<String> {
        return mNoteAdded
    }

    fun setNoteAdded(mText : String) {
        mNoteAdded.set(mText)
    }

    fun getIsCarRental() : ObservableBoolean {
        return mIsCarRental
    }

    fun setIsCarRental(isCarRental : Boolean) {
        mIsCarRental.set(isCarRental)
    }

    fun onAddNote() {
        mView?.onAddNot()
    }

    /*** Init Data Taxi **/
    private val mIsHaveAddOnService = ObservableBoolean(true)

    fun getIsHaveAddOnService() : ObservableBoolean {
        return mIsHaveAddOnService
    }

    fun setIsHaveAddOnService(mIsHaveAddOnService : Boolean) {
        this.mIsHaveAddOnService.set(mIsHaveAddOnService)
    }


    fun updateTotalPrice(priceBeforeExtra: Double, mServiceOptionList: ArrayList<CarRentalRecommendModel> ) {
        var total = priceBeforeExtra
        mServiceOptionList.forEach {
            total = FormatPriceHelper.addPrice(total, it.price ?: 0.0)
        }

        mPriceTotal.set(FormatPriceHelper.getDisplayPrice(mContext, "USD", total))
    }


    fun initDataCarTaxi(
        mContext: Context,
        mItemBinding: SuggestedRideCarRentalLayoutBinding,
        mDataTaxi: CarRentalSuggestedRideModel) {
        val viewModel = SuggestedRideCarRentalViewModel(mContext, mDataTaxi, object : OnClickItemCallBackListener {
            override fun onAddItem(mCarRentalSuggestedRideModel: CarRentalSuggestedRideModel) {

            }

            override fun onMinusItem(mCarRentalSuggestedRideModel: CarRentalSuggestedRideModel) {

            }

            override fun onClickViewDetail(mCarRentalSuggestedRideModel: CarRentalSuggestedRideModel) {

            }

        })

        // Gone button View Detail
        viewModel.setIsCanViewDetail(false)

        // Gone button add
        viewModel.setIsIsCanAdd(false)

        // Gone button minus
        viewModel.setIsIsCanMinus(false)

        // Set Background Card
        viewModel.setIsCardBackground(false)


        mItemBinding.setVariable(BR.viewModel, viewModel)
    }
}