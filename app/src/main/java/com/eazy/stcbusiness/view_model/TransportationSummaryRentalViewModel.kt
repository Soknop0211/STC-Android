package com.eazy.stcbusiness.view_model

import android.app.Application
import android.content.Context
import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.databinding.SuggestedRideCarRentalLayoutBinding
import com.eazy.stcbusiness.model.CarRentalRecommendModel
import com.eazy.stcbusiness.model.CarRentalSuggestedRideModel
import com.eazy.stcbusiness.model.TicketAvailableModel
import com.eazy.stcbusiness.network_module.ApiRepository
import com.eazy.stcbusiness.utils.AppLOGG
import com.eazy.stcbusiness.utils.FormatPriceHelper
import com.eazy.stcbusiness.utils.Utils
import com.eazy.stcbusiness.utils.getDisplayPrice
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnClickListenerSummeryRental : OnClickCallBackListener {
    fun onClickPickUpLocationCallBack()
}

@HiltViewModel
class TransportationSummaryRentalViewModel @Inject constructor(
    private val mContext: Application,
    private val apiRepository : ApiRepository
) : BaseViewModel<OnClickListenerSummeryRental>(){

    private val mAddressPickUp = ObservableField<String>()

    val mLatPickUp = ObservableField<Double>()
    val mLongPickUP = ObservableField<Double>()
    fun getAddressPickUp() : ObservableField<String> {
        return mAddressPickUp
    }

    fun setAddressPickUp(mAddressPickUp : String) {
        this.mAddressPickUp.set(mAddressPickUp)
    }

    init {
        setTitle(mContext.getString(R.string.summary))
        setIsShowPriceOnly(true)
        mPriceTotal.set(String.format(" %s %s", "390.00", "USD"))
        mAddressPickUp.set(mContext.getString(R.string.pick_up))
    }

    /**** Car Recommend ****/
    private val _itemList = MutableLiveData<ArrayList<CarRentalRecommendModel>>()
    val itemList: LiveData<ArrayList<CarRentalRecommendModel>> get() = _itemList


    fun initList() {
        val mListSubList = ArrayList<CarRentalRecommendModel>()
        var mItem = CarRentalRecommendModel(image = Utils.getUrlFromResource(R.drawable.transportation_tuktuk, mContext.packageName), name = "Tuk tuk", description = "Traditional motorbike trailer 1-4 seats", price = 120.50)
        mItem.id = "tuttuk"
        mListSubList.add(mItem)

        mItem = CarRentalRecommendModel(image = Utils.getUrlFromResource(R.drawable.transporatation_passapp, mContext.packageName), name = "Pass App", description = "3-Wheel-Auto Riskhaw 1-3 seats", price = 35.50)
        mItem.id = "passapp"
        mListSubList.add(mItem)

        mItem = CarRentalRecommendModel(image = Utils.getUrlFromResource(R.drawable.transportation_shared, mContext.packageName), name = "Shared", description = "Shared rides with others going your way", price = 50.00)
        mItem.id = "shared"
        mListSubList.add(mItem)

        mItem = CarRentalRecommendModel(image = Utils.getUrlFromResource(R.drawable.transportation_deluxe, mContext.packageName), name = "Deluxe", description = "Newer cars with extra legroom", price = 95.30)
        mItem.id = "deluxe"
        mListSubList.add(mItem)

        mItem = CarRentalRecommendModel(image = Utils.getUrlFromResource(R.drawable.transportation_supreme, mContext.packageName), name = "Supreme", description = "Luxury rides for 6 with professional drivers", price = 140.00)
        mItem.id = "supreme"
        mListSubList.add(mItem)

        _itemList.value = mListSubList

    }

    /*** Init Data Taxi **/
    fun initDataCarTaxi(mContext: Context,
                        mItemBinding : SuggestedRideCarRentalLayoutBinding,
                        mDataTaxi : CarRentalSuggestedRideModel,
                        onClickCallBack : (CarRentalSuggestedRideModel) -> Unit) {
        val viewModel = SuggestedRideCarRentalViewModel(mContext, mDataTaxi, object : OnClickItemCallBackListener {
            override fun onAddItem(mCarRentalSuggestedRideModel: CarRentalSuggestedRideModel) {
                onClickCallBack.invoke(mCarRentalSuggestedRideModel)
            }

            override fun onMinusItem(mCarRentalSuggestedRideModel: CarRentalSuggestedRideModel) {
                onClickCallBack.invoke(mCarRentalSuggestedRideModel)
            }

            override fun onClickViewDetail(mCarRentalSuggestedRideModel: CarRentalSuggestedRideModel) {

            }

        })

        mItemBinding.setVariable(BR.viewModel, viewModel)
    }

    private val mPriceBeforeExtra = ObservableField<Double>(0.0)

    fun getPriceBeforeExtra() : ObservableField<Double> {
        return mPriceBeforeExtra
    }

    fun updateTotalPrice(priceBeforeExtra: Double, mServiceOptionList: ArrayList<CarRentalRecommendModel> ) {
        mPriceBeforeExtra.set(priceBeforeExtra)

        var total = priceBeforeExtra
        mServiceOptionList.forEach {
            if(it.isSelectedItem == true) {
                total = FormatPriceHelper.addPrice(total, it.price ?: 0.0)
            }
        }

        mPriceTotal.set(String.format(" %s %s", total, "USD"))
    }

    override fun onClickBookNow() {
        mView?.onClickCallBack()
    }

    fun onClickPickUpLocationCallBack() {
        mView?.onClickPickUpLocationCallBack()
    }

}


/*** Item Car Service Option **/
class CarRentalServiceOptionItemViewModel(mContext : Context,
                                          mCarRentalRecommendModel : CarRentalRecommendModel,
                                          private val mOnClickListener : (CarRentalRecommendModel) -> Unit) {

    private val mTitle = ObservableField<String>()
    private val mDescription = ObservableField<String>()
    private var mCarRentalRecommendModel : CarRentalRecommendModel? = null

    private val mTotalPrice = ObservableField<String>()

    private val mSelected = ObservableBoolean(false)

    init {
        initItem(mContext, mCarRentalRecommendModel)
    }

    private fun initItem(mContext : Context, mCarRentalRecommendModel : CarRentalRecommendModel) {
        mTitle.set(mCarRentalRecommendModel.name)
        mDescription.set(mCarRentalRecommendModel.description)
        mTotalPrice.set(mContext.getDisplayPrice("USD", mCarRentalRecommendModel.price ?: 0.0))

        this.mCarRentalRecommendModel = mCarRentalRecommendModel
    }

    fun getTitle(): ObservableField<String> {
        return mTitle
    }

    fun getDescription(): ObservableField<String> {
        return mDescription
    }

    fun getTotalPrice() : ObservableField<String> {
        return mTotalPrice
    }

    fun onButtonClick() {
        if (mCarRentalRecommendModel != null) {
            mOnClickListener.invoke(mCarRentalRecommendModel!!)
        }
    }

    fun setSelected(isSelected : Boolean) {
        mSelected.set(isSelected)
    }

    fun getSelected(): ObservableBoolean {
        return mSelected
    }


}