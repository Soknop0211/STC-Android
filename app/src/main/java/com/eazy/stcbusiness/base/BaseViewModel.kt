package com.eazy.stcbusiness.base



abstract class BaseViewModel<T : BaseView> : BaseViewModelObservation() {

    protected var mView: T? = null

    fun bind(baseView: T) {
        this.mView = baseView
    }

    override fun onCleared() {
        super.onCleared()
        mView = null
    }
}