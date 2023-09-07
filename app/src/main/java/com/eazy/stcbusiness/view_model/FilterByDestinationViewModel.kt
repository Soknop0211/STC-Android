package com.eazy.stcbusiness.view_model

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.model.LocationModel

class FilterByDestinationViewModel(
    isShowImage: Boolean,
    mLocation: LocationModel,
    private val mOnClick: (String) -> Unit
) {

    private val mTitle = ObservableField<String>()
    private val mIsVisibleLine = ObservableBoolean(false)
    private val mSelected = ObservableBoolean()
    private var mId : String ?= null
    private var mShowIcon : Boolean = false
    private val mRatingSelected = ObservableBoolean(false)

    private val mIsSelected = ObservableBoolean()
    fun getIsSelected(): ObservableBoolean {
        return mIsSelected
    }

    fun updateSelection(isSelected: Boolean) {
        mIsSelected.set(isSelected)
    }
    init {
        initItem(isShowImage, mLocation)
    }

    private fun initItem(isShowImage: Boolean, mLocation : LocationModel) {
        mId = mLocation.id
        mShowIcon = isShowImage
        mTitle.set(mLocation.name)
        mIsVisibleLine.set(isShowImage)
        mSelected.set(mLocation.isClicked)
    }

    fun getTitle(): ObservableField<String> {
        return mTitle
    }

    fun getVisible(): ObservableBoolean {
        return mIsVisibleLine
    }

    fun setSelected(isSelected : Boolean) {
        mSelected.set(isSelected)
    }

    fun getSelected(): ObservableBoolean {
        return mSelected
    }

    fun getShowRating(): ObservableBoolean {
        return mRatingSelected
    }

    fun initListener(mListLocation : ArrayList<LocationModel>, mId : String) {
        for (mValue in mListLocation) {
            mValue.isClicked = mValue.id == mId
            mSelected.set(mValue.isClicked)
        }
    }

    fun onItemClicked() {
        // mOnClick.invoke(mId ?: "")
    }

}
