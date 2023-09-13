package com.eazy.stcbusiness.view_model

import android.app.Application
import android.content.res.Resources
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.model.CustomCategoryDataList
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.model.SavePlaceModel
import com.eazy.stcbusiness.network_module.ApiRepository
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransportationDestinationViewModel  @Inject constructor(
    mContext: Application,
    private val apiRepository : ApiRepository
) : BaseViewModel<OnClickCallBackListener>() {

    val mTitle = ObservableField<String>()

    private val _itemList = MutableLiveData<ArrayList<SavePlaceModel>>()
    val itemList: LiveData<ArrayList<SavePlaceModel>> get() = _itemList

    fun initList(resources : Resources) {
        val mListSubList = ArrayList<SavePlaceModel>()
        var drawable = ResourcesCompat.getDrawable(resources, R.drawable.home_booking, null)
        mListSubList.add(SavePlaceModel("mHome", "Home", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.home_map, null)
        mListSubList.add(SavePlaceModel("mTripPlace", "Trip Place", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.home_user, null)
        mListSubList.add(SavePlaceModel("mSave", "Save", drawable!!))

        _itemList.value = mListSubList

    }

    fun onClickChooseMap() {
        mView?.onClickCallBack()
    }
}