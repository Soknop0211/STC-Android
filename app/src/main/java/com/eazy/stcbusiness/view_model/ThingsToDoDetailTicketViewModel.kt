package com.eazy.stcbusiness.view_model

import android.content.Context
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.utils.getDisplayPrice
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThingsToDoDetailTicketViewModel @Inject constructor() : BaseViewModel<BaseView>(){

    init {
        setTitle("Detail")
    }

    fun initAction() {}

    val mPriceTotal = ObservableField<String>()

    fun setPriceTotal(mPrice : Double, mContext : Context) {
        mPriceTotal.set(mContext.getDisplayPrice("USD", mPrice).plus(" / "))
    }

    fun onClickBookNow() {

    }

    fun onClickSelectDate() {

    }

    fun onClickPeople() {

    }

}