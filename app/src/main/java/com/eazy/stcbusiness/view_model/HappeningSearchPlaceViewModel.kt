package com.eazy.stcbusiness.view_model

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.model.ItemCategoryDateModel
import com.eazy.stcbusiness.model.ItemRecentSearchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnListener : BaseView {
    fun onSearchClick()
}

@HiltViewModel
class HappeningSearchPlaceViewModel @Inject constructor(private val mContext: Application) : BaseViewModel<OnListener>(){

}

// Item Search
class ItemRecentSearchViewModel(
    mLocation: ItemRecentSearchModel,
    mListener : (ItemRecentSearchModel) -> Unit
) {

    private val mName = ObservableField<String>()
    private val mDescription = ObservableField<String>()
    private var mListener : ((ItemRecentSearchModel) -> Unit?)? = null
    private var mLocation: ItemRecentSearchModel ?= null

    init {
        initItem(mLocation, mListener)
    }

    private fun initItem(mLocation : ItemRecentSearchModel, mListener : (ItemRecentSearchModel) -> Unit) {
        mName.set(mLocation.name)
        mDescription.set(mLocation.description)
        this.mListener = mListener
        this.mLocation = mLocation
    }

    fun getName(): ObservableField<String> {
        return mName
    }

    fun getDescription(): ObservableField<String> {
        return mDescription
    }

    fun onClickListener() {
        mLocation?.let { mListener?.invoke(it) }
    }
}