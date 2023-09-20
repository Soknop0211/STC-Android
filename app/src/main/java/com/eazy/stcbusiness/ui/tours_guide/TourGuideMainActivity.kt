package com.eazy.stcbusiness.ui.tours_guide

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.databinding.ActivityTourGuideMainBinding
import com.eazy.stcbusiness.model.LocationModel
import com.eazy.stcbusiness.ui.todo_ui.fragment.DestinationLocationBottomSheetFragment
import com.eazy.stcbusiness.ui.tours_guide.TourGuideDetailActivity.Companion.gotoTourGuideDetailActivity
import com.eazy.stcbusiness.ui.transportation.car_rental.ChangeLanguageBottomSheetFragment
import com.eazy.stcbusiness.ui.transportation.car_rental.ChangeLanguageBottomSheetFragment.Companion.gotoChangeLanguageBottomSheetFragment
import com.eazy.stcbusiness.ui.transportation.car_rental.adapter.TourGuideItemListAdapter
import com.eazy.stcbusiness.view_model.OnClickItemListener
import com.eazy.stcbusiness.view_model.TourGuideViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TourGuideMainActivity : BaseActivity<ActivityTourGuideMainBinding, TourGuideViewModel>(),
    OnClickItemListener {

    override val layoutId =  R.layout.activity_tour_guide_main
    override val mViewModel: TourGuideViewModel by viewModels()

    companion object {
        fun gotoTourGuideMainActivity(activity: Context){
            val intent = Intent(activity, TourGuideMainActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        mViewModel.initList()
        mViewModel.itemList.observe(this) {
            mBinding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@TourGuideMainActivity)

                adapter = TourGuideItemListAdapter(it) {
                    gotoTourGuideDetailActivity(this@TourGuideMainActivity)
                }
            }
        }

    }

    private fun initViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)

        mViewModel.initList()

    }

    override fun onLanguageClick() {
        mViewModel.initListLanguage()

        mViewModel.itemListLang.observe(this) {
            gotoChangeLanguageBottomSheetFragment(supportFragmentManager, it, object :
                ChangeLanguageBottomSheetFragment.OnClickCallBackListener {
                override fun onClickSelectLocation(mLocationModel: LocationModel) {
                    mViewModel.setLanguage(mLocationModel.name ?: "")
                }

            })
        }
    }

    override fun onDestinationClick() {
        DestinationLocationBottomSheetFragment.gotoDestinationLocationBottomSheetFragment(supportFragmentManager,
        object : DestinationLocationBottomSheetFragment.OnClickCallBackListener {
            override fun onClickSelectLocation(mLocationModel : LocationModel) {
                mViewModel.setDestination(mLocationModel.name ?: "")
            }

        }, DestinationLocationBottomSheetFragment.ONLY_DESTINATION)
    }

    override fun onClickCallBack() {

    }
}