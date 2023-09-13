package com.eazy.stcbusiness.ui.transportation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.activity.result.ActivityResult
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.base.BetterActivityResult
import com.eazy.stcbusiness.base.SampleBaseActivity
import com.eazy.stcbusiness.databinding.ActivityTransportationDestinataionBinding
import com.eazy.stcbusiness.databinding.CustomTextViewLayoutBinding
import com.eazy.stcbusiness.model.ItemRecentSearchModel
import com.eazy.stcbusiness.model.SavePlaceModel
import com.eazy.stcbusiness.ui.happening_ui.adapter.HappeningItemRecentSearchAdapter
import com.eazy.stcbusiness.ui.transportation.TransportationBookNowActivity.Companion.ACTION
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import com.eazy.stcbusiness.view_model.TransportationDestinationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransportationDestinationActivity : BaseActivity<ActivityTransportationDestinataionBinding, TransportationDestinationViewModel>(),
    OnClickCallBackListener {

    override val layoutId = R.layout.activity_transportation_destinataion
    override val mViewModel: TransportationDestinationViewModel by viewModels()

    companion object {
        fun gotoTransportationDestinationActivity(activity: Context){
            val intent = Intent(activity, TransportationDestinationActivity::class.java)
            activity.startActivity(intent)
        }

        fun gotoTransportationDestinationActivity(
            activity: Activity,
            activityResult: BetterActivityResult.OnActivityResult<ActivityResult>,
        ) {
            val intent = Intent(activity, TransportationDestinationActivity::class.java)
            if (activity is SampleBaseActivity) {
                activityResult.let {
                    activity.activityLauncher.launch(intent, activityResult)
                }
            }
        }

        var LATITUDE = "LATITUDE"
        var LONGITUDE = "LONGITUDE"
        var ADDRESS = "ADDRESS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        // Search Data
        val list = ArrayList<ItemRecentSearchModel>()
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))

        mBinding.categoryRecycle.apply {
            layoutManager = LinearLayoutManager(this@TransportationDestinationActivity)
            adapter = HappeningItemRecentSearchAdapter(list) {

            }
        }

        // Saved Place
        mViewModel.initList(resources)
        mViewModel.itemList.observe(this) {
            for (mItem in it) {
                addSavePlace(mItem, this)
            }
        }

    }

    private fun addSavePlace(mSavePlaceModel : SavePlaceModel, mContext: Context){
        val binding: CustomTextViewLayoutBinding = CustomTextViewLayoutBinding
            .inflate(LayoutInflater.from(mContext))
        binding.icon.visibility = View.VISIBLE
        binding.icon.setImageDrawable(mSavePlaceModel.icon)
        binding.txt.text = mSavePlaceModel.name
        val lp = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(10, 0, 10, 0)
        binding.mainLayout.layoutParams = lp
        mBinding.peopleAdded.addView(binding.root)
    }


    private fun initViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)
    }

    override fun onClickCallBack() {
        TransportationBookNowActivity.gotoTransportationBookNowActivity(this,
            ACTION,
            object : BetterActivityResult.OnActivityResult<ActivityResult> {
                override fun onActivityResult(result: ActivityResult) {
                    if (result.resultCode == RESULT_OK) {
                        val intent = result.data
                        if (intent != null && intent.hasExtra(LATITUDE)) {
                            val mIntent = Intent()
                            mIntent.putExtra(LATITUDE,  intent.getDoubleExtra(LATITUDE, 0.0))
                            mIntent.putExtra(LONGITUDE,  intent.getDoubleExtra(LONGITUDE, 0.0))
                            mIntent.putExtra(ADDRESS,  intent.getStringExtra(ADDRESS) ?: "")
                            setResult(RESULT_OK, mIntent)
                            finish()
                        }
                    }
                }

            })
    }
}