package com.eazy.stcbusiness.network_module

import com.eazy.stcbusiness.base.ApiResWraper
import com.eazy.stcbusiness.model.LocationItemModel
import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {

    @GET("api/locations/fetch_all_locations")
    suspend fun getPropertyService() : Response<ApiResWraper<ArrayList<LocationItemModel>>>

}