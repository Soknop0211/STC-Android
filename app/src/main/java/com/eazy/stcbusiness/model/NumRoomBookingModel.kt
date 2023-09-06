package com.eazy.stcbusiness.model

import java.io.Serializable

class NumRoomBookingModel(var numValues: String?, var action: String?) : Serializable {
    var room : String? = null
    var adults : String? = null
    var children : String? = null
}
