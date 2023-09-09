package com.eazy.stcbusiness.view_model

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.model.ItemCategoryDateModel
import com.eazy.stcbusiness.model.LocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnItemClickListener : BaseView {
    fun onFilterClick()
}


@HiltViewModel
class HappeningEventUpViewModel @Inject constructor(private val mContext: Application) : BaseViewModel<OnItemClickListener>(){

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

    fun onClickFilter() {
        mView!!.onFilterClick()
    }

}

// item category
class ItemCategoryViewModel(
    mLocation: ItemCategoryDateModel
) {

    private val mNumDay = ObservableField<String>()
    private val mDay = ObservableField<String>()
    private val mSelected = ObservableBoolean()
    private var mId : String ?= null

    private val mIsSelected = ObservableBoolean()

    fun getIsSelected(): ObservableBoolean {
        return mIsSelected
    }

    fun updateSelection(isSelected: Boolean) {
        mIsSelected.set(isSelected)
    }
    init {
        initItem(mLocation)
    }

    private fun initItem(mLocation : ItemCategoryDateModel) {
        mId = mLocation.id
        mDay.set(mLocation.nameDay)
        mNumDay.set(mLocation.numDay)
    }

    fun getNumDay(): ObservableField<String> {
        return mNumDay
    }

    fun getDay(): ObservableField<String> {
        return mDay
    }

    fun setSelected(isSelected : Boolean) {
        mSelected.set(isSelected)
    }

    fun getSelected(): ObservableBoolean {
        return mSelected
    }


}
