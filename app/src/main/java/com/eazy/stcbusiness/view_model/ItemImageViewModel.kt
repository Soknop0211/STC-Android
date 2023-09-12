package com.eazy.stcbusiness.view_model

import android.graphics.drawable.Drawable
import androidx.databinding.ObservableField

class ItemImageViewModel (icon : Drawable, title : String, status : String, mOnClick : (String) -> Unit) {
    val mUrl = ObservableField<Drawable>()
    val mTitle = ObservableField<String>()
    val mStatus = ObservableField<String>()
    private var mOnClick : ((String) -> Unit?)? = null
    init {
        mUrl.set(icon)
        mTitle.set(title)
        mStatus.set(status)
        this.mOnClick = mOnClick
    }

    fun onClickCallBack() {
        mOnClick?.invoke("ON_CLICK")
    }
}