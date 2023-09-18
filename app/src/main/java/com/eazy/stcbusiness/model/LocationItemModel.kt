package com.eazy.stcbusiness.model

import java.io.Serializable

data class LocationItemModel(
    var location_id : String? = null,
    var location_name : String? = null,
    var image : String? = null,
    var total_hotels : String? = null
) : Serializable
