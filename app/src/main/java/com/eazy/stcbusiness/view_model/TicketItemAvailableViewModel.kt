package com.eazy.stcbusiness.view_model

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.eazy.stcbusiness.model.TicketAvailableModel
import com.eazy.stcbusiness.utils.getDisplayPrice

class TicketItemAvailableViewModel(mContext : Context, mTicketAvailableModel : TicketAvailableModel, private val mOnClickListener : (TicketAvailableModel?) -> Unit) {

    private val mTitle = ObservableField<String>()
    private val mDescription = ObservableField<String>()
    private var mTicketAvailableModel : TicketAvailableModel ? = null

    private val mStartTime = ObservableField<String>()

    private val mEndTime = ObservableField<String>()

    private val mIsClicked = ObservableBoolean(false)

    private val mTotalPrice = ObservableField<String>()

    private val mStatus = ObservableField<String>()


    init {
        initItem(mContext, mTicketAvailableModel)
    }

    private fun initItem(mContext : Context, mTicketAvailableModel : TicketAvailableModel) {
        mTitle.set(mTicketAvailableModel.title)
        mDescription.set(mTicketAvailableModel.description)
        mStartTime.set(mTicketAvailableModel.startDate)
        mIsClicked.set(mTicketAvailableModel.isClick)
        mEndTime.set(mTicketAvailableModel.endDate)
        mStatus.set(mTicketAvailableModel.status)
        mTotalPrice.set(mContext.getDisplayPrice("USD", mTicketAvailableModel.price).plus(" / "))

        this.mTicketAvailableModel = mTicketAvailableModel
    }

    fun getTitle(): ObservableField<String> {
        return mTitle
    }

    fun getDescription(): ObservableField<String> {
        return mDescription
    }

    fun getStartTime(): ObservableField<String> {
        return mStartTime
    }

    fun getEndTime(): ObservableField<String> {
        return mEndTime
    }

    fun getIsClicked() : ObservableBoolean {
        return mIsClicked
    }

    fun getTotalPrice() : ObservableField<String> {
        return mTotalPrice
    }

    fun getStatus() : ObservableField<String> {
        return mStatus
    }

    fun onButtonClick() {
        mOnClickListener.invoke(mTicketAvailableModel)
    }

}