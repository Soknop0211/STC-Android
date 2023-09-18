package com.eazy.stcbusiness.network_module

import android.app.Application
import com.eazy.stcbusiness.base.ApiResWraper
import com.eazy.stcbusiness.base.NetworkResult
import com.eazy.stcbusiness.base.handleApi
import com.eazy.stcbusiness.model.LocationItemModel
import com.eazy.stcbusiness.model.MainCategoryServiceModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

interface ApiRepository {
    suspend fun fetchLocation() : NetworkResult<ApiResWraper<ArrayList<LocationItemModel>>>
}

class ApiImplementationWs @Inject constructor(private val mContext : Application, private val retrofit : ServiceApi) : ApiRepository {
    override suspend fun fetchLocation(): NetworkResult<ApiResWraper<ArrayList<LocationItemModel>>> {
        return handleApi {
            retrofit.getPropertyService()
        }
    }

//        val response = retrofit.getPropertyService()
//        if (response.success){
//            return ApiResWraper(data = response.data)
//        } else {
//            return ApiResWraper(errors = JsonObject())
//        }
//        else {
//            return ApiResWraper(errors = errorResponse(mContext.baseContext, response))
//        }

}