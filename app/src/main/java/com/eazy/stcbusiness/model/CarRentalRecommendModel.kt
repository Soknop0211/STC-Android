package com.eazy.stcbusiness.model

data class CarRentalRecommendModel (
    val image: String? = null,
    var name : String? = null,
    var description : String?= null,
    var price : Double ?= null,
    ) : BaseModel()