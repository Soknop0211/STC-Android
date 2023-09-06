package com.eazy.stcbusiness.model

data class TicketAvailableModel (
    var title : String? = null,
    var status : String? = null,
    var description : String? = null,
    var startDate : String? = null,
    var endDate : String? = null,
    var price : Double,
    var isClick : Boolean = false
    ) : BaseModel()