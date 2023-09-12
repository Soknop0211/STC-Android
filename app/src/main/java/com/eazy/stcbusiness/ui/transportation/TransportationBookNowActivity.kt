package com.eazy.stcbusiness.ui.transportation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.databinding.ActivityTransportationBookNowBinding
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import com.eazy.stcbusiness.view_model.TransportationBookingNowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransportationBookNowActivity : BaseActivity<ActivityTransportationBookNowBinding, TransportationBookingNowViewModel>(),
    OnClickCallBackListener {

    override val layoutId = R.layout.activity_transportation_book_now
    override val mViewModel: TransportationBookingNowViewModel by viewModels()

    companion object {
        fun gotoTransportationBookNowActivity(activity: Context){
            val intent = Intent(activity, TransportationBookNowActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    init {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)
    }

    override fun onClickCallBack() {

    }
}