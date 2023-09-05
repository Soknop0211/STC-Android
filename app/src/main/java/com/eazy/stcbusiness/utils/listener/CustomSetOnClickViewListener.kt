package com.eazy.stcbusiness.utils.listener

import android.view.View

class CustomSetOnClickViewListener
    (private val customResponseOnClickListener: CustomResponseOnClickListener) : View.OnClickListener {

    override fun onClick(view: View) {
        view.isEnabled = false
        view.postDelayed({ view.isEnabled = true }, 500)
        customResponseOnClickListener.onClick(view)
    }
}