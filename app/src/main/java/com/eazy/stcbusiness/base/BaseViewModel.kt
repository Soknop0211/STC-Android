package com.eazy.stcbusiness.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T : BaseView> : ViewModel() {

    protected var mView: T? = null

    fun bind(baseView: T) {
        this.mView = baseView
    }

    override fun onCleared() {
        super.onCleared()
        mView = null
    }
}