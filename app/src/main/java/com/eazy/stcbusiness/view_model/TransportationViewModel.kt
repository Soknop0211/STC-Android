package com.eazy.stcbusiness.view_model

import android.app.Application
import android.content.res.Resources
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.model.CustomCategoryDataList
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.network_module.ApiRepository
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransportationViewModel @Inject constructor(
    mContext: Application,
    private val apiRepository : ApiRepository
) : BaseViewModel<OnClickCallBackListener>(){

    init {
        setTitle(mContext.getString(R.string.taxi))
    }

    private val _itemList = MutableLiveData<ArrayList<CustomCategoryDataList>>()
    val itemList: LiveData<ArrayList<CustomCategoryDataList>> get() = _itemList


    fun initList(resources : Resources) {
        val mList = ArrayList<CustomCategoryDataList>()

        var mListSubList = ArrayList<CustomCategoryModel>()
        var drawable = ResourcesCompat.getDrawable(resources, R.drawable.taxi_recommend, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.taxi_recommend, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.taxi_recommend, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.TRANSPORTATION_FOR_YOU, "Book Now", drawable!!))
        mList.add(CustomCategoryDataList(HomeContentFragment.TRANSPORTATION_FOR_YOU, resources.getString(R.string.for_you), mListSubList, true))

        mListSubList = ArrayList()
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.transportation, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.TRANSPORTATION_LOOKING_FOR, "Book Now", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.transportation, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.TRANSPORTATION_LOOKING_FOR, "Schedule\nBook", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.transport_rent, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.TRANSPORTATION_LOOKING_FOR, "Transportation\nRental", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.airport, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.TRANSPORTATION_LOOKING_FOR, "Airport\nTransfer", drawable!!))
        mList.add(CustomCategoryDataList(HomeContentFragment.TRANSPORTATION_LOOKING_FOR, resources.getString(R.string.what_are_you_looking_for), mListSubList, true))

        _itemList.value = mList

    }



}