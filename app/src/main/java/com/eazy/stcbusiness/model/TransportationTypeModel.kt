package com.eazy.stcbusiness.model

class TransportationTypeModel(var name : String? = null,
                              var status : String? = null,
                              var image : String ?= null,
                              var iconDrawable : Int ?= null,
                              var price : Double ?= null,
                              var description  : String ?= null) : BaseModel()