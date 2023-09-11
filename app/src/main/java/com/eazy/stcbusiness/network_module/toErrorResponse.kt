package com.eazy.stcbusiness.network_module

import android.content.Context
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.utils.AppLOGG
import com.eazy.stcbusiness.utils.showToast
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.ResponseBody
import org.json.JSONException
import retrofit2.Response
import java.io.IOException


val Response<*>.errorResponse: ErrorResponse
    get() {
        this.errorBody()?.toErrorResponse?.let {
            if (it.msg.isNullOrEmpty()) {
                it.msg = "Something when wrong !"
            }
            it.errorCode = this.code()
            try {
                if (it.errorCode == 401){
                    if (it.msg!!.contains("Unauthenticated", true)) {
                        // CustomCallback.popupSessionExpired()
                        return it
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                AppLOGG.d("jfgdf" + e.javaClass.name, e.message + "")
                return it
            } catch (e2: IOException) {
                AppLOGG.d(e2.javaClass.name, e2.message + "")
                return it
            }
            return it
        } ?: run {
            if (this.errorResponse.msg.isNullOrEmpty()) {
                this.errorResponse.msg?.let { AppLOGG.d("jeeeeeeeeeeeeeeeeeee", it) }
            }
            return ErrorResponse(false, this.code(), this.message())
        }
    }

private val ResponseBody?.toErrorResponse: ErrorResponse?
    get() {
        return Gson().fromJson(this?.string(), ErrorResponse::class.java)
    }

fun <T> errorResponse(@ApplicationContext mContext: Context, response : Response<T>) : ErrorResponse {
    response.errorBody()?.toErrorResponse?.let {
        run {
            if (it.msg.isNullOrEmpty()) {
                it.msg = mContext.getString(R.string.something_went_wrong)
            }
            it.errorCode = response.code()
            try {
                if (it.errorCode == 401){
                    if (it.msg!!.equals("Unauthorized", true)) {
                        mContext.showToast("Unauthorized Session")
                        return it
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                AppLOGG.d("jfgdf" + e.javaClass.name, e.message + "")
                return it
            } catch (e2: IOException) {
                AppLOGG.d(e2.javaClass.name, e2.message + "")
                return it
            }
            return it
        }
    } ?: run {
        if (response.errorResponse.msg.isNullOrEmpty()) {
            response.errorResponse.msg?.let { AppLOGG.d("jeeeeeeeeeeeeeeeeeee", it) }
        }
        return ErrorResponse(false, response.code(), response.message())
    }
}
