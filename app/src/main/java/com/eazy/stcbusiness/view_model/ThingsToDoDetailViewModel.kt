package com.eazy.stcbusiness.view_model

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.base.BaseViewModel
import com.eazy.stcbusiness.base.NetworkResult
import com.eazy.stcbusiness.model.LocationItemModel
import com.eazy.stcbusiness.network_module.ApiRepository
import com.eazy.stcbusiness.utils.AppLOGG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface OnDetailButtonListener : BaseView {
    fun onBookNowClick()
}

@HiltViewModel
class ThingsToDoDetailViewModel @Inject constructor(
    val repository: ApiRepository
) : BaseViewModel<OnDetailButtonListener>(){

    private val mName = ObservableField<String>()
    private val mDescription = ObservableField<String>()
    private val mTermCondition = ObservableField<String>()

    init {
        setTitle("Detail")

        mName.set("Klook")
        mDescription.set("Siem Reap is a cheerful city that embraces travelers like old friends. This region was the site of successive capitals of the Khmer Siem Reap is a cheerful city that embraces travelers like old friends. This region was the site of successive capitals of the Khmer")
        mTermCondition.set("Siem Reap is a cheerful city that embraces travelers like old friends. This region was the site of successive capitals of the Khmer Siem Reap is a cheerful city that embraces travelers like old friends. This region was the site of successive capitals of the Khmer")
    }

    fun getName(): ObservableField<String> {
        return mName
    }

    fun getDescription(): ObservableField<String> {
        return mDescription
    }

    fun getTermCondition(): ObservableField<String> {
        return mTermCondition
    }

    override fun onClickBookNow() {
        if (mView != null) {
            mView?.onBookNowClick()
        }
    }

    private val _loading = MutableLiveData<Boolean>()
    private val _dataListAll =
        MutableLiveData<ArrayList<LocationItemModel>>()

    val loading: MutableLiveData<Boolean> get() = _loading
    val dataListAll: MutableLiveData<ArrayList<LocationItemModel>> get() = _dataListAll

    fun getBindingList () {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                setLoading(true)
                when  (val resultRes = repository.fetchLocation()) {
                    is NetworkResult.ApiSuccess -> {
                        _dataListAll.postValue(resultRes.data.data!!)
                        setLoading(false)
                    }
                    is NetworkResult.ApiError -> {
                        // errorFlow.emit("${resultRes.code} ${resultRes.message}")
                        val error = "${resultRes.code} ${resultRes.message}"
                        AppLOGG.d("jeeeeeeeeeeeeeeeeeeeeee", error)
                        setLoading(false)
                    }
                    is NetworkResult.ApiException -> {
                        // errorFlow.emit("${resultRes.e.message}")
                        val error = "${resultRes.e.message}"
                        AppLOGG.d("jeeeeeeeeeeeeeeeeeeeeee", error)
                        setLoading(false)
                    }
                }

            }
        }
    }

}