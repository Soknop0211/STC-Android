package com.eazy.stcbusiness.network_module

import android.app.Application
import com.eazy.stcbusiness.model.MainCategoryServiceModel
import com.google.gson.JsonElement
import javax.inject.Inject

interface ApiRepository {
    suspend fun fetchLocation() : ApiRespond<JsonElement>
}

class ApiImplementationWs @Inject constructor(private val mContext : Application, private val retrofit : ServiceApi) : ApiRepository {
    override suspend fun fetchLocation(): ApiRespond<JsonElement> {
        val response = retrofit.getPropertyService()
        return if (response.isSuccessful && response.body() != null) {
            ApiRespond(result = response.body())
        } else {
            ApiRespond(error = errorResponse(mContext.baseContext, response))
        }
    }
}