package com.eazy.stcbusiness.network_module

data class ApiRespond <T>(
    val result: T? = null,
    var errorCode : T?= null,
    val error: ErrorResponse? = null
)