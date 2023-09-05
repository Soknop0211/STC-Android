package com.eazy.stcbusiness.base

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T : ViewDataBinding>(protected var mBinding: T) :
    RecyclerView.ViewHolder(mBinding.root) {

    protected val mContext: Context
        get() = mBinding.root.context

    protected fun getString(resId: Int): String {
        return mContext.getString(resId)
    }

    protected fun getColor(resId: Int): Int {
        return ContextCompat.getColor(mContext, resId)
    }

    protected fun getDimensionPixelOffset(resId: Int): Int {
        return mContext.resources.getDimensionPixelOffset(resId)
    }

    protected fun getDimension(resId: Int): Float {
        return mContext.resources.getDimension(resId)
    }

    protected fun getDrawable(resId: Int): Drawable? {
        return ContextCompat.getDrawable(mContext, resId)
    }
}