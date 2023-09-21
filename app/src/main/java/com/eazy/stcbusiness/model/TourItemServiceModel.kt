package com.eazy.stcbusiness.model

data class TourItemServiceModel (
    val image: String? = null,
    var name : String? = null,
    var description : String?= null,
    var price : Double ?= null,
    var isSelectedItem : Boolean ?= false
) : BaseModel()