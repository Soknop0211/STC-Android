package com.eazy.stcbusiness.view_model

import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThingsToDoDetailViewModel @Inject constructor() : BaseViewModel<BaseView>(){

    init {
        setTitle("Detail")
    }

    fun initAction() {}

}