package com.eazy.stcbusiness.view_model

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.model.TourItemServiceModel
import com.eazy.stcbusiness.utils.getDisplayPrice


class TourGuideServiceItemViewModel(mContext : Context,
                                    mTourItemServiceModel : TourItemServiceModel,
                                    private val mOnClickListener : (TourItemServiceModel) -> Unit) {

    private val mTitle = ObservableField<String>()
    private val mDescription = ObservableField<String>()
    private var mTourItemServiceModel : TourItemServiceModel? = null

    private val mTotalPrice = ObservableField<String>()

    private val mSelected = ObservableBoolean(false)

    private val mUrl = ObservableField<String>()

    init {
        initItem(mContext, mTourItemServiceModel)
    }

    private fun initItem(mContext : Context, mTourItemServiceModel : TourItemServiceModel) {
        mTitle.set(mTourItemServiceModel.name)
        mDescription.set(mTourItemServiceModel.description)
        mTotalPrice.set(mContext.getDisplayPrice("USD", mTourItemServiceModel.price ?: 0.0))
        mUrl.set(mTourItemServiceModel.image)
        this.mTourItemServiceModel = mTourItemServiceModel
    }

    fun getUrl(): ObservableField<String> {
        return mUrl
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

    fun setSelected(isSelected : Boolean) {
        mSelected.set(isSelected)
    }

    fun getSelected(): ObservableBoolean {
        return mSelected
    }


    fun onClickViewDetail() {
        if (mTourItemServiceModel != null){
            mOnClickListener.invoke(mTourItemServiceModel!!)
        }
    }

}