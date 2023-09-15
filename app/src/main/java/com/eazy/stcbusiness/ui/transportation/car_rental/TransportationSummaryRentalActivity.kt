package com.eazy.stcbusiness.ui.transportation.car_rental

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.base.BetterActivityResult
import com.eazy.stcbusiness.databinding.ActivityTransportationMainCarRentalBinding
import com.eazy.stcbusiness.databinding.ActivityTransportationSummaryRentalBinding
import com.eazy.stcbusiness.databinding.SuggestedRideCarRentalLayoutBinding
import com.eazy.stcbusiness.databinding.TransportationItemTypeLayoutBinding
import com.eazy.stcbusiness.model.CarRentalRecommendModel
import com.eazy.stcbusiness.model.CarRentalSuggestedRideModel
import com.eazy.stcbusiness.model.TicketAvailableModel
import com.eazy.stcbusiness.model.TransportationTypeModel
import com.eazy.stcbusiness.ui.todo_ui.adapter.TicketAvailableAdapter
import com.eazy.stcbusiness.ui.transportation.adapter.TaxiServiceOptionItemAdapter
import com.eazy.stcbusiness.ui.transportation.booknow.TransportationBookNowActivity
import com.eazy.stcbusiness.ui.transportation.booknow.TransportationDestinationActivity
import com.eazy.stcbusiness.utils.AppLOGG
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import com.eazy.stcbusiness.view_model.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransportationSummaryRentalActivity : BaseActivity<ActivityTransportationSummaryRentalBinding, TransportationSummaryRentalViewModel>(),
    OnClickListenerSummeryRental {


    companion object {
        fun gotoTransportationSummaryRentalActivity(activity: Context){
            val intent = Intent(activity, TransportationSummaryRentalActivity::class.java)
            activity.startActivity(intent)
        }

        fun gotoTransportationSummaryRentalActivity(activity: Context, mCarRentalSuggestedRideModel : CarRentalSuggestedRideModel){
            val intent = Intent(activity, TransportationSummaryRentalActivity::class.java)
            intent.putExtra(DATA, mCarRentalSuggestedRideModel)
            activity.startActivity(intent)
        }

        const val DATA = "DATA"
    }

    override val layoutId: Int = R.layout.activity_transportation_summary_rental
    override val mViewModel: TransportationSummaryRentalViewModel by viewModels()

    private var mServiceOptionList: ArrayList<CarRentalRecommendModel> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBaseViewModel()

        // Init list
        mViewModel.initList()
        mViewModel.itemList.observe(this) {
            mServiceOptionList = it

            initRecyclerView()
        }

    }

    private fun initRecyclerView() {
        val mAdapter = TaxiServiceOptionItemAdapter(mServiceOptionList) {
            // Update Price
            mViewModel.updateTotalPrice(mViewModel.getPriceBeforeExtra().get() ?: 0.0, mServiceOptionList) // Calculate Price
        }

        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TransportationSummaryRentalActivity)
            adapter = mAdapter
        }
    }

    private fun initBaseViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)

        // init data
        val mDataTaxi = getDataFromModelClass<CarRentalSuggestedRideModel>(DATA, this)
        val mItemBinding : SuggestedRideCarRentalLayoutBinding = mBinding.mDataTaxi

        if (mDataTaxi != null) {
            // Update Price
            mViewModel.updateTotalPrice(mDataTaxi.price ?: 0.0, mServiceOptionList) // Calculate Price

            mViewModel.initDataCarTaxi(this, mItemBinding, mDataTaxi) {
                // Update Price
                mViewModel.updateTotalPrice(mDataTaxi.price ?: 0.0, mServiceOptionList) // Calculate Price
            }
        }
    }

    override fun onClickPickUpLocationCallBack() {
        TransportationPickUpLocationActivity.gotoTransportationPickUpLocationActivity(this,
            object : BetterActivityResult.OnActivityResult<ActivityResult> {
                override fun onActivityResult(result: ActivityResult) {
                    if (result.resultCode == RESULT_OK) {
                        val intent = result.data
                        if (intent != null && intent.hasExtra(TransportationDestinationActivity.LATITUDE)) {
                            mViewModel.mLatPickUp.set(intent.getDoubleExtra(
                                TransportationDestinationActivity.LATITUDE, 0.0))
                            mViewModel.mLongPickUP.set(intent.getDoubleExtra(
                                TransportationDestinationActivity.LONGITUDE, 0.0))
                            mViewModel.setAddressPickUp(intent.getStringExtra(
                                TransportationDestinationActivity.ADDRESS
                            ) ?: "")
                        }
                    }
                }

            })
    }

    override fun onClickCallBack() {
        mServiceOptionList.forEach {
            if(it.isSelectedItem == true) {
                AppLOGG.d("keeeeeeeeeeeeeeeeeee", it.name ?: "")
            }
        }
    }

}