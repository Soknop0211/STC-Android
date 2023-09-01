package com.eazy.stcbusiness.model

import android.graphics.drawable.Drawable
import java.io.Serializable

class CustomCategoryModel : Serializable {
    constructor(id: String?, name: String?, isClick: Boolean) {
        this.id = id
        this.name = name
        this.isClick = isClick
    }

    constructor(id: String?, name: String?) {
        this.id = id
        this.name = name
    }

    constructor(id: String?, name: String?, description: String?, urlImage: String?) {
        this.id = id
        this.name = name
        this.description = description
        this.urlImage = urlImage
    }

    constructor(id: String?, name: String?, icon : Drawable) {
        this.id = id
        this.name = name
        this.icon = icon
    }

    constructor()

    var id : String?= null
    var name : String?= null
    var description : String?= null
    var icon : Drawable?= null
    var urlImage : String?= null
    var isClick : Boolean = false
}

data class CustomCategoryDataList(val mainAction : String,
                                  val title : String,
                                  val data : ArrayList<CustomCategoryModel>,
                                  val isHaveHeader : Boolean = false) : Serializable