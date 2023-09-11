package com.eazy.stcbusiness.ui.happening_ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.databinding.ActivityHappeningNowDetailBinding
import com.eazy.stcbusiness.model.ItemImageSliderModel
import com.eazy.stcbusiness.model.ItemRecentSearchModel
import com.eazy.stcbusiness.ui.happening_ui.adapter.HappeningItemRecentSearchAdapter
import com.eazy.stcbusiness.ui.happening_ui.adapter.ShowPeopleItemAdapter
import com.eazy.stcbusiness.view_model.HappeningNowDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class HappeningNowDetailActivity : BaseActivity<ActivityHappeningNowDetailBinding, HappeningNowDetailViewModel>(),
    BaseView {

    override val layoutId: Int = R.layout.activity_happening_now_detail
    override val mViewModel: HappeningNowDetailViewModel by viewModels()

    companion object {
        fun gotoHappeningNowDetailActivity(activity: Context){
            val intent = Intent(activity, HappeningNowDetailActivity::class.java)
            activity.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)

        var imageList = arrayListOf(
            "https://dev.booknow.asia/images/home_slider_1.jpg",
            "https://dev.booknow.asia/images/home_slider_3.jpg",
            "https://dev.booknow.asia/images/home_slider_4.jpg",
            "https://dev.booknow.asia/images/home_slider_5.jpg",
            "https://dev.booknow.asia/images/home_slider_5.jpg",
            "https://dev.booknow.asia/images/home_slider_5.jpg",
            "https://dev.booknow.asia/images/home_slider_5.jpg",
            "https://dev.booknow.asia/images/home_slider_5.jpg",
            "https://dev.booknow.asia/images/home_slider_5.jpg",
            "https://dev.booknow.asia/images/home_slider_6.jpg"
        )

        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HappeningNowDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = ShowPeopleItemAdapter(imageList, false)
        }

        imageList = arrayListOf(
            "https://dev.booknow.asia/images/home_slider_1.jpg",
            "https://dev.booknow.asia/images/home_slider_3.jpg",
            "https://dev.booknow.asia/images/home_slider_4.jpg",
            "https://dev.booknow.asia/images/home_slider_5.jpg",
        )


        mBinding.recyclerViewSponsor.apply {
            layoutManager = GridLayoutManager(this@HappeningNowDetailActivity, 4)
            adapter = ShowPeopleItemAdapter(imageList, true)
        }
    }
}