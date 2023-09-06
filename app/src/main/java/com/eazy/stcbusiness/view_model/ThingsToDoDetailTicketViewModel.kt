package com.eazy.stcbusiness.view_model

import android.app.Application
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.base.SampleBaseActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnTicketListener : BaseView {
    fun onDateSelectClick()
    fun onPeopleSelectClick()
}

@HiltViewModel
class ThingsToDoDetailTicketViewModel @Inject constructor(private val mContext: Application) : BaseViewModel<OnTicketListener>(){

    init {
        setTitle("Detail")
    }


    val mDate = ObservableField<String>()
    val mStartDate = ObservableField<String>()
    val mEndDate = ObservableField<String>()
    val mPerson = ObservableField<String>()

    fun setSetDate(mStartDate : String, mEndDate : String) {
        mDate.set(String.format("%s", mStartDate))
    }

    fun onClickSelectDate() {
        if (mView != null) {
            mView?.onDateSelectClick()
        }
    }

    fun onClickPeople() {
        if (mView != null) {
            mView?.onPeopleSelectClick()
        }
    }



}