package com.eazy.stcbusiness.view_model

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

class ShowImageViewModel(mUrl: String, isShow : Boolean, isSponsor : Boolean) {

    private val mUrl = ObservableField<String>()
    private val mIsShowIcon = ObservableBoolean(false)
    private val mSponsor = ObservableBoolean(false)

    init {
        initItem(mUrl, isShow, isSponsor)
    }

    private fun initItem(url: String, isShow : Boolean, isSponsor : Boolean) {
        mUrl.set(url)
        mIsShowIcon.set(isShow)
        mSponsor.set(isSponsor)
    }

    fun getImageUrl(): ObservableField<String> {
        return mUrl
    }

    fun getShowIcon(): ObservableBoolean {
        return mIsShowIcon
    }

    fun getShowSponsor(): ObservableBoolean {
        return mSponsor
    }

}