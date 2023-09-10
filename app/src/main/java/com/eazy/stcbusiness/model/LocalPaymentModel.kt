package com.eazy.stcbusiness.model

import android.graphics.drawable.Drawable

data class LocalPaymentModel (
    var id : String? = null,
    val drawable: Drawable? = null,
    var name : String? = null,
    var description : String,
    var isClick : Boolean,
    ) : java.io.Serializable