package com.eazy.stcbusiness.view_model

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.model.CarRentalRecommendModel
import com.eazy.stcbusiness.model.LanguageModel
import com.eazy.stcbusiness.model.LocationModel
import com.eazy.stcbusiness.model.TourGuideItemModel
import com.eazy.stcbusiness.network_module.ApiRepository
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.utils.FormatPriceHelper
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnClickItemListener : OnClickCallBackListener {
    fun onLanguageClick()
    fun onDestinationClick()
}

@HiltViewModel
class TourGuideViewModel @Inject constructor(
    mContext: Application,
    private val apiRepository : ApiRepository
) : BaseViewModel<OnClickItemListener>(){

    private val _itemList = MutableLiveData<ArrayList<TourGuideItemModel>>()
    val itemList: LiveData<ArrayList<TourGuideItemModel>> get() = _itemList

    private val mLanguage = ObservableField<String>()
    private val mDestination = ObservableField<String>()

    init {
        setTitle(mContext.getString(R.string.explore_tours_guide))
        setDestination(mContext.getString(R.string.destination))
        setLanguage(mContext.getString(R.string.language))
    }

    fun setLanguage(mLang : String) {
        mLanguage.set(mLang)
    }

    fun getLanguage() : ObservableField<String>{
        return mLanguage
    }

    fun setDestination(mDes : String) {
        mDestination.set(mDes)
    }

    fun getDestination() : ObservableField<String>{
        return mDestination
    }

    fun initList() {
        val mListSubList = ArrayList<TourGuideItemModel>()
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        mListSubList.add(TourGuideItemModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now"))
        _itemList.value = mListSubList

    }

    /**** Car Recommend ****/
    private val _itemListLang = MutableLiveData<ArrayList<LanguageModel>>()
    val itemListLang: LiveData<ArrayList<LanguageModel>> get() = _itemListLang

    fun initListLanguage() {

        val mList = ArrayList<LanguageModel>()

        var mModel = LanguageModel()
        mModel.id = "khmer"
        mModel.name = "Khmer"
        mList.add(mModel)

        mModel = LanguageModel()
        mModel.id = "english"
        mModel.name = "English"
        mList.add(mModel)

        mModel = LanguageModel()
        mModel.id = "japanese "
        mModel.name = "Japanese "
        mList.add(mModel)

        mModel = LanguageModel()
        mModel.id = "china "
        mModel.name = "China "
        mList.add(mModel)

        mModel = LanguageModel()
        mModel.id = "thai "
        mModel.name = "Thai "
        mList.add(mModel)

        _itemListLang.value = mList

    }

    fun onLanguageClick() {
        mView?.onLanguageClick()
    }

    fun onDestinationClick() {
        mView?.onDestinationClick()
    }

}


// Item Tour Guide
class TourGuideItemViewModel (mContext: Context, mCarRentalRecommendModel : CarRentalRecommendModel, mOnClick : (String) -> Unit) {
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
