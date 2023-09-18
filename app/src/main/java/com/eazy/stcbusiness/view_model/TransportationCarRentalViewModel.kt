package com.eazy.stcbusiness.view_model

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.model.*
import com.eazy.stcbusiness.network_module.ApiRepository
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.utils.FormatPriceHelper
import com.eazy.stcbusiness.utils.Utils
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransportationCarRentalViewModel @Inject constructor(
    private val mContext: Application,
    private val apiRepository : ApiRepository
) : BaseViewModel<OnClickCallBackListener>(){

    init {
        setTitle(mContext.getString(R.string.car_renal))
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

    /**** Car Suggested ****/
    private val _itemListSuggested = MutableLiveData<ArrayList<CarRentalSuggestedRideModel>>()
    val itemListSuggested: LiveData<ArrayList<CarRentalSuggestedRideModel>> get() = _itemListSuggested


    fun initListSuggest() {
        val mListSubList = ArrayList<CarRentalSuggestedRideModel>()
        var mItem = CarRentalSuggestedRideModel(image = Utils.getUrlFromResource(R.drawable.transportation_tuktuk, mContext.packageName), name = "Tuk tuk", description = "Traditional motorbike trailer 1-4 seats", price = 120.50, priceBase = 120.50)
        mItem.id = "tuttuk"
        mListSubList.add(mItem)

        mItem = CarRentalSuggestedRideModel(image = Utils.getUrlFromResource(R.drawable.transporatation_passapp, mContext.packageName), name = "Pass App", description = "3-Wheel-Auto Riskhaw 1-3 seats", price = 35.50, priceBase = 35.50)
        mItem.id = "passapp"
        mListSubList.add(mItem)

        mItem = CarRentalSuggestedRideModel(image = Utils.getUrlFromResource(R.drawable.transportation_shared, mContext.packageName), name = "Shared", description = "Shared rides with others going your way", price = 50.00, priceBase = 50.00)
        mItem.id = "shared"
        mListSubList.add(mItem)

        mItem = CarRentalSuggestedRideModel(image = Utils.getUrlFromResource(R.drawable.transportation_deluxe, mContext.packageName), name = "Deluxe", description = "Newer cars with extra legroom", price = 95.30, priceBase = 95.30)
        mItem.id = "deluxe"
        mListSubList.add(mItem)

        mItem = CarRentalSuggestedRideModel(image = Utils.getUrlFromResource(R.drawable.transportation_supreme, mContext.packageName), name = "Supreme", description = "Luxury rides for 6 with professional drivers", price = 140.00, priceBase = 140.00)
        mItem.id = "supreme"
        mListSubList.add(mItem)

        _itemListSuggested.value = mListSubList

    }

}

// Recommend Rental
class RecommendCarRentalViewModel (mContext: Context, mCarRentalRecommendModel : CarRentalRecommendModel, mOnClick : (String) -> Unit) {
    val mUrl = ObservableField<String>()
    val mTitle = ObservableField<String>()
    val mDescription = ObservableField<String>()
    val mPrice = ObservableField<String>()

    private var mOnClick : ((String) -> Unit?)? = null

    init {
        mUrl.set(mCarRentalRecommendModel.image)
        mTitle.set(mCarRentalRecommendModel.name)
        mDescription.set(mCarRentalRecommendModel.description)
        mPrice.set(String.format("%s %s", FormatPriceHelper.getDisplayPrice(mContext, "USD", mCarRentalRecommendModel.price ?: 0.0), "/"))
        this.mOnClick = mOnClick
    }

    fun onClickCallBack() {
        mOnClick?.invoke("ON_CLICK")
    }
}

// Suggested Ride
interface OnClickItemCallBackListener {
    fun onAddItem(mCarRentalSuggestedRideModel : CarRentalSuggestedRideModel)
    fun onMinusItem(mCarRentalSuggestedRideModel : CarRentalSuggestedRideModel)
    fun onClickViewDetail(mCarRentalSuggestedRideModel : CarRentalSuggestedRideModel)
}

class SuggestedRideCarRentalViewModel (mContext : Context, mCarRentalSuggestedRideModel : CarRentalSuggestedRideModel, mOnClick : OnClickItemCallBackListener) {
    val mUrl = ObservableField<String>()
    val mTitle = ObservableField<String>()
    val mDescription = ObservableField<String>()
    val mPrice = ObservableField<String>()
    private val mNumberCount = ObservableField<String>()
    private var mItemCounter = 0
    private var mCarRentalSuggestedRideModel : CarRentalSuggestedRideModel ?= null
    private var mOnClick : OnClickItemCallBackListener
    private var mContext : Context ?= null

    // Validate option
    private val mIsCanAdd = ObservableBoolean(true)
    private val mIsCanMinus = ObservableBoolean(true)
    private val mIsCanViewDetail = ObservableBoolean(true)
    private val mIsCardBackground = ObservableBoolean(false)

    fun getIsCanAdd() : ObservableBoolean {
        return mIsCanAdd
    }

    fun setIsIsCanAdd(mIsCanAdd : Boolean) {
        this.mIsCanAdd.set(mIsCanAdd)
    }

    fun getIsCanMinus() : ObservableBoolean {
        return mIsCanMinus
    }

    fun setIsIsCanMinus(mIsCanMinus : Boolean) {
        this.mIsCanMinus.set(mIsCanMinus)
    }

    fun getIsCanViewDetail() : ObservableBoolean {
        return mIsCanViewDetail
    }

    fun setIsCanViewDetail(mIsCanViewDetail : Boolean) {
        this.mIsCanViewDetail.set(mIsCanViewDetail)
    }

    fun getIsCardBackground() : ObservableBoolean {
        return mIsCardBackground
    }

    fun setIsCardBackground(mIsCardBackground : Boolean) {
        this.mIsCardBackground.set(mIsCardBackground)
    }

    init {
        mUrl.set(mCarRentalSuggestedRideModel.image)
        mTitle.set(mCarRentalSuggestedRideModel.name)
        mDescription.set(mCarRentalSuggestedRideModel.description)
        this.mOnClick = mOnClick
        this.mContext = mContext
        this.mCarRentalSuggestedRideModel = mCarRentalSuggestedRideModel

        // Init Price
        mPrice.set(String.format("%s %s", FormatPriceHelper.getDisplayPrice(mContext, "USD", mCarRentalSuggestedRideModel.price ?: 0.0), "/"))
        if (mCarRentalSuggestedRideModel.mItemCount > 0) {
            mItemCounter = mCarRentalSuggestedRideModel.mItemCount
            mNumberCount.set(mItemCounter.toString())
        } else {
            mNumberCount.set(1.toString())
        }
    }

    fun onClickAddItem() {
        mItemCounter++
        mNumberCount.set(mItemCounter.toString())
        mCarRentalSuggestedRideModel?.mItemCount = mItemCounter

        mCarRentalSuggestedRideModel?.setItemCount(mItemCounter)

        mPrice.set(String.format("%s %s", FormatPriceHelper.getDisplayPrice(mContext!!, "USD", mCarRentalSuggestedRideModel?.price ?: 0.0), "/"))

        if (mCarRentalSuggestedRideModel != null){
            mOnClick.onAddItem(mCarRentalSuggestedRideModel!!)
        }
    }

    fun onClickMinusItem() {
        if (mItemCounter > 0 && mItemCounter != 1) {
            mItemCounter--
            mNumberCount.set(mItemCounter.toString())

            mCarRentalSuggestedRideModel?.mItemCount = mItemCounter

            mCarRentalSuggestedRideModel?.setItemCount(mItemCounter)

            if (mItemCounter != 0) {
                mNumberCount.set(mItemCounter.toString())
                mPrice.set(String.format("%s %s", FormatPriceHelper.getDisplayPrice(mContext!!, "USD", mCarRentalSuggestedRideModel?.price ?: 0.0), "/"))
            } else {
                mPrice.set(FormatPriceHelper.getDisplayPrice(mContext!!, "USD", 0.0))
                mNumberCount.set(0.toString())
            }
        }

        if (mCarRentalSuggestedRideModel != null){
            mOnClick.onMinusItem(mCarRentalSuggestedRideModel!!)
        }
    }

    fun getNumberCount() : ObservableField<String> {
        return mNumberCount
    }

    fun onClickViewDetail() {
        if (mCarRentalSuggestedRideModel != null){
            mOnClick.onClickViewDetail(mCarRentalSuggestedRideModel!!)
        }
    }
}