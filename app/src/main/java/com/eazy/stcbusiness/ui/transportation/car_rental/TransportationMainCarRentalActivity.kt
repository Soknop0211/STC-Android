package com.eazy.stcbusiness.ui.transportation.car_rental

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.databinding.ActivityTransportationMainCarRentalBinding
import com.eazy.stcbusiness.ui.transportation.car_rental.TransportationRecommendDetailBottomSheetFragment.Companion.gotoTransportationRecommend
import com.eazy.stcbusiness.ui.transportation.car_rental.adapter.CarRentalRecommendAdapter
import com.eazy.stcbusiness.ui.transportation.car_rental.adapter.CarRentalSuggestedAdapter
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import com.eazy.stcbusiness.view_model.TransportationCarRentalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransportationMainCarRentalActivity : BaseActivity<ActivityTransportationMainCarRentalBinding, TransportationCarRentalViewModel>(),
    OnClickCallBackListener {

    companion object {
        fun gotoTransportationMainCarRentalActivity(activity: Context){
            val intent = Intent(activity, TransportationMainCarRentalActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override val layoutId: Int get() = R.layout.activity_transportation_main_car_rental

    override val mViewModel: TransportationCarRentalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        mViewModel.itemList.observe(this) {
            mBinding.recyclerViewRecommend.apply {
                layoutManager = LinearLayoutManager(this@TransportationMainCarRentalActivity, LinearLayoutManager.HORIZONTAL, false)
                adapter = CarRentalRecommendAdapter(it) {
                    gotoTransportationRecommend(supportFragmentManager, it) /** gotoTransportationRecommend **/
                }
            }
        }


        mViewModel.itemListSuggested.observe(this) {
            mBinding.recyclerViewSuggest.apply {
                layoutManager = LinearLayoutManager(this@TransportationMainCarRentalActivity)
                adapter = CarRentalSuggestedAdapter(it) {

                }
            }
        }



    }

    private fun initViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)

        mViewModel.initList()

        mViewModel.initListSuggest()
    }

    override fun onClickCallBack() {

    }

}