package com.eazy.stcbusiness.view_model

import android.app.Application
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.model.UserPeopleModel
import com.eazy.stcbusiness.utils.Utils
import com.eazy.stcbusiness.utils.listener.BaseEdittextListener
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnButtonListener : BaseView {
    fun onClickCallBack(model: UserPeopleModel)
}

@HiltViewModel
class AddPeopleViewModel @Inject constructor(private val mContext: Application) : BaseViewModel<OnButtonListener>(){

    init {

    }

    val mValidButton = ObservableBoolean(false)
    val mFirstName = ObservableField<String>()
    val mLastName = ObservableField<String>()
    val mPhoneNumber = ObservableField<String>()
    val mEmail = ObservableField<String>()

    fun onClickButtonAdd() {
        val mModel = UserPeopleModel(mFirstName.get(), mLastName.get(), mPhoneNumber.get(), mEmail.get())
        mView?.onClickCallBack(mModel)
    }

    fun onInputChangeListener(): TextWatcher {
        return object : BaseEdittextListener() {
            override fun afterTextChangedNotEmpty(editable: Editable) {

            }

            override fun afterTextChangedIsEmpty() {

            }

        }
    }

    private fun checkToEnableActionButton() {
        if (shouldEnableActionButton()) {
            if (!mValidButton.get()) mValidButton.set(true)
        } else {
            if (mValidButton.get()) mValidButton.set(false)
        }
    }

    private fun shouldEnableActionButton(): Boolean {
        return (!TextUtils.isEmpty(mFirstName.get())
                && !TextUtils.isEmpty(mLastName.get())
                && !Utils.isEmailAddress(mEmail.get()))
                && !Utils.phoneNumberValidate(mPhoneNumber.get())
    }

}