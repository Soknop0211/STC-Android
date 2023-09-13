package com.eazy.stcbusiness.view_model

import android.app.Application
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnCheckOutCallBackListener : OnClickCallBackListener {
    fun onAddPeople()
    fun onAddNot()
}

@HiltViewModel
class HappeningNowCheckOutViewModel @Inject constructor(private val mContext: Application) : BaseViewModel<OnCheckOutCallBackListener>(){

    val mUrl = ObservableField<String>()

    init {
        setTitle("Checkout")

        mUrl.set("https://dev.booknow.asia/images/home_slider_1.jpg")

        mPriceTotal.set(String.format("%s %s%s", " ", 8.00, "USD"))
    }

    override fun onClickBookNow() {
        mView?.onClickCallBack()
    }

    fun onAddPeople() {
        mView?.onAddPeople()
    }

    // Transportation
    private var mNoteAdded = ObservableField<String>()

    fun getNoteAdded() : ObservableField<String> {
        return mNoteAdded
    }

    fun setNoteAdded(mText : String) {
        mNoteAdded.set(mText)
    }

    fun onAddNote() {
        mView?.onAddNot()
    }
}