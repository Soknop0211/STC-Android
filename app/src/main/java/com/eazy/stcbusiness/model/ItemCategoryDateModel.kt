package com.eazy.stcbusiness.model

import java.io.Serializable

data class ItemCategoryDateModel(
    var id : String? = null,
    var nameDay : String? = null,
    var numDay : String,
) : Serializable