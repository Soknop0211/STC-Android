package com.eazy.stcbusiness.utils.listener

import android.view.View

interface DelayClickListener {

    fun onDelayClick(view: View)

    companion object {
        const val DELAY_DURATION = 500
    }
}