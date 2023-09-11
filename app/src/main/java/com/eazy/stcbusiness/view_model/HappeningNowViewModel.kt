package com.eazy.stcbusiness.view_model

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.network_module.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnItemListener : BaseView {
   fun onSearchClick()
}

@HiltViewModel
class HappeningNowViewModel @Inject constructor(
    private val mContext: Application,
    private val apiRepository : ApiRepository
) : BaseViewModel<OnItemListener>(){

    private val _itemList = MutableLiveData<ArrayList<CustomCategoryModel>>()
    val itemList: LiveData<ArrayList<CustomCategoryModel>> get() = _itemList

    init {
        initList()
    }

    private fun initList() {
        val mList = ArrayList<CustomCategoryModel>()
        mList.add(CustomCategoryModel("1", "Phnom Penh", "10 Activities", "https://dev.booknow.asia/images/home_slider_1.jpg"))
        mList.add(CustomCategoryModel("2", "Siem Reap", "11 Activities", "https://dev.booknow.asia/images/home_slider_2.jpg"))
        mList.add(CustomCategoryModel("3", "Kep, Province","11 Activities", "https://dev.booknow.asia/images/home_slider_3.jpg"))
        mList.add(CustomCategoryModel("4", "Takeo", "10 Activities", "https://dev.booknow.asia/images/home_slider_4.jpg"))
        mList.add(CustomCategoryModel("5", "Kompong Spie", "11 Activities", "https://dev.booknow.asia/images/home_slider_5.jpg"))
        mList.add(CustomCategoryModel("6", "Kompot","11 Activities", "https://dev.booknow.asia/images/home_slider_6.jpg"))
        _itemList.value = mList
    }

}