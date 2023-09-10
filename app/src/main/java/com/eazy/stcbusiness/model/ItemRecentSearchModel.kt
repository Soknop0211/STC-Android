package com.eazy.stcbusiness.model

import java.io.Serializable

data class ItemRecentSearchModel (
    var id : String? = null,
    var name : String? = null,
    var description : String,
) : Serializable