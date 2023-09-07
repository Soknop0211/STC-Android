package com.eazy.stcbusiness.view_model

import androidx.databinding.ObservableField
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.model.ToDoDetailModel
import com.eazy.stcbusiness.model.YouWillKnow
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnDetailButtonListener : BaseView {
    fun onBookNowClick()
}

@HiltViewModel
class ThingsToDoDetailViewModel @Inject constructor() : BaseViewModel<OnDetailButtonListener>(){

    private val mName = ObservableField<String>()
    private val mDescription = ObservableField<String>()
    private val mTermCondition = ObservableField<String>()

    init {
        setTitle("Detail")

        mName.set("Klook")
        mDescription.set("Siem Reap is a cheerful city that embraces travelers like old friends. This region was the site of successive capitals of the Khmer Siem Reap is a cheerful city that embraces travelers like old friends. This region was the site of successive capitals of the Khmer")
        mTermCondition.set("Siem Reap is a cheerful city that embraces travelers like old friends. This region was the site of successive capitals of the Khmer Siem Reap is a cheerful city that embraces travelers like old friends. This region was the site of successive capitals of the Khmer")
    }

    fun getName(): ObservableField<String> {
        return mName
    }

    fun getDescription(): ObservableField<String> {
        return mDescription
    }

    fun getTermCondition(): ObservableField<String> {
        return mTermCondition
    }

    override fun onClickBookNow() {
        if (mView != null) {
            mView?.onBookNowClick()
        }
    }

}