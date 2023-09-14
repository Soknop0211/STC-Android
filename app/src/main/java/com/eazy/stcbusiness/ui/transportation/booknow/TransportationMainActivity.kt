package com.eazy.stcbusiness.ui.transportation.booknow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.base_dapter.MainHomeShowItemAdapter
import com.eazy.stcbusiness.databinding.ActivityTransportationMainBinding
import com.eazy.stcbusiness.ui.transportation.booknow.TransportationBookNowActivity.Companion.gotoTransportationBookNowActivity
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import com.eazy.stcbusiness.view_model.TransportationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransportationMainActivity : BaseActivity<ActivityTransportationMainBinding, TransportationViewModel>(),
    OnClickCallBackListener {

    companion object {
        fun gotoTransportationMainActivity(activity: Context){
            val intent = Intent(activity, TransportationMainActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override val layoutId: Int get() = R.layout.activity_transportation_main

    override val mViewModel: TransportationViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        mViewModel.itemList.observe(this) {
            mBinding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@TransportationMainActivity)
                adapter = MainHomeShowItemAdapter(it , this@TransportationMainActivity, {
                }, { mModel, mString -> // On Item Click
                    if (mModel.id == "book_schedule") {
                        gotoTransportationBookNowActivity(this@TransportationMainActivity, mString)
                    } else if (mModel.id == "book_now"){
                        gotoTransportationBookNowActivity(this@TransportationMainActivity)
                    }
                } ) }
        }

    }


    private fun initViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)

        mViewModel.initList(resources)
    }

    override fun onClickCallBack() {

    }
}