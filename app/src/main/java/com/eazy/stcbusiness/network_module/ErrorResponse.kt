package com.eazy.stcbusiness.network_module

import com.google.gson.annotations.SerializedName

class ErrorResponse(
    var success: Boolean?,
    @SerializedName("status_code")
    var errorCode: Int?,
    @SerializedName("msg")
    var msg: String?
)