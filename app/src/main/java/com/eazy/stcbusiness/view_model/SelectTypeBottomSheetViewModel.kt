package com.eazy.stcbusiness.view_model

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.model.TransportationTypeModel
import com.eazy.stcbusiness.utils.Utils
import com.eazy.stcbusiness.utils.getDisplayPrice
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface OnClickConfirmItemType : OnClickCallBackListener {
    fun onDismiss()
}

@HiltViewModel
class TransportationSelectTypeBottomSheetViewModel @Inject constructor(mContext: Application) : BaseViewModel<OnClickConfirmItemType>(){

    private val _itemList = MutableLiveData<ArrayList<TransportationTypeModel>>()
    val itemList: LiveData<ArrayList<TransportationTypeModel>> get() = _itemList
    private var mTransportationTypeModel : TransportationTypeModel ?= null
    fun getTransportationTypeModel() : TransportationTypeModel? {
        return mTransportationTypeModel
    }

    fun getTransportationTypeModel(mTransportationTypeModel : TransportationTypeModel) {
        this.mTransportationTypeModel = mTransportationTypeModel
    }

    fun initList() {
        val mListSubList = ArrayList<TransportationTypeModel>()
        var mItem = TransportationTypeModel(name = "Tuk tuk", description = "Traditional motorbike trailer 1-4 seats", price = 120.50, iconDrawable = R.drawable.transportation_tuktuk)
        mItem.id = "tuttuk"
        mListSubList.add(mItem)

        mItem = TransportationTypeModel(name = "Pass App", description = "3-Wheel-Auto Riskhaw 1-3 seats", price = 35.50, iconDrawable = R.drawable.transporatation_passapp)
        mItem.id = "passapp"
        mListSubList.add(mItem)

        mItem = TransportationTypeModel(name = "Shared", status = "New", description = "Shared rides with others going your way", price = 50.00, iconDrawable = R.drawable.transportation_shared)
        mItem.id = "shared"
        mListSubList.add(mItem)

        mItem = TransportationTypeModel(name = "Deluxe", description = "Newer cars with extra legroom", price = 95.30, iconDrawable = R.drawable.transportation_deluxe)
        mItem.id = "deluxe"
        mListSubList.add(mItem)

        mItem = TransportationTypeModel(name = "Supreme", description = "Luxury rides for 6 with professional drivers", price = 140.00, iconDrawable = R.drawable.transportation_supreme)
        mItem.id = "supreme"
        mListSubList.add(mItem)

        _itemList.value = mListSubList

    }

    fun onClickConfirmButton() {
        mView?.onClickCallBack()
    }

    override fun onDismissButton() {
        mView?.onDismiss()
    }
}

// Item Type View Model
class SelectTypeBottomSheetViewModel (
    private val mContext : Context,
    mTransportationTypeModel: TransportationTypeModel,
    private val mOnClick: (TransportationTypeModel) -> Unit) {

    private val mTitle = ObservableField<String>()
    private val mStatus = ObservableField<String>()
    private val mDescription = ObservableField<String>()
    private val mPrice = ObservableField<String>()
    private val mImage = ObservableField<String>()
    private val mSelected = ObservableBoolean()
    private var mTransportationTypeModel: TransportationTypeModel ?= null

    init {
        initItem(mTransportationTypeModel)
    }

    private fun initItem(mTransportationTypeModel : TransportationTypeModel) {
        mTitle.set(mTransportationTypeModel.name)
        mDescription.set(mTransportationTypeModel.description)
        mPrice.set(mContext.getDisplayPrice(mTransportationTypeModel.price ?: 0.0, "USD"))

        if (mTransportationTypeModel.image != null) {
            mImage.set(mTransportationTypeModel.image)
        } else if (mTransportationTypeModel.iconDrawable != null){
            mImage.set(Utils.getUrlFromResource(mTransportationTypeModel.iconDrawable ?: R.drawable.no_image, mContext.packageName))
        }

        this.mTransportationTypeModel = mTransportationTypeModel
    }

    fun getTitle(): ObservableField<String> {
        return mTitle
    }

    fun getStatus(): ObservableField<String> {
        return mStatus
    }

    fun getDescription(): ObservableField<String> {
        return mDescription
    }

    fun getImage(): ObservableField<String> {
        return mImage
    }

    fun getPrice(): ObservableField<String> {
        return mPrice
    }

    fun setSelected(isSelected : Boolean) {
        mSelected.set(isSelected)
    }

    fun getSelected(): ObservableBoolean {
        return mSelected
    }

    fun onItemClicked() {
        if (mTransportationTypeModel != null) {
            mOnClick.invoke(mTransportationTypeModel!!)
        }
    }

}