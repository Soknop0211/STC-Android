package com.eazy.stcbusiness.model


data class HappeningDetailModel (
    var name : String? = null,
    var location : String? = null,
    var images : String?= null
) : BaseModel()