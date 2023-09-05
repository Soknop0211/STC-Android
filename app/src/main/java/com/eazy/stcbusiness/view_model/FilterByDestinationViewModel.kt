package com.eazy.stcbusiness.view_model

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.model.LocationModel

class FilterByDestinationViewModel(mSelectedId: String, mLocation : LocationModel) {

    private val mTitle = ObservableField<String>()
    private val mIsVisibleLine = ObservableBoolean()

    init {
        initItem(mSelectedId, mLocation)
    }

    private fun initItem(mSelectedId : String, mLocation : LocationModel) {
        mTitle.set(mLocation.name)
        mIsVisibleLine.set(mSelectedId == mLocation.id)
    }

    fun getTitle(): ObservableField<String> {
        return mTitle
    }

    fun getVisible(): ObservableBoolean {
        return mIsVisibleLine
    }
}