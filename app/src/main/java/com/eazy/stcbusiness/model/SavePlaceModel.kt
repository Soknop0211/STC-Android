package com.eazy.stcbusiness.model

import android.graphics.drawable.Drawable
import java.io.Serializable

open class SavePlaceModel(var id : String? = null,
                          var name : String?= null,
                          var icon : Drawable? = null) : Serializable