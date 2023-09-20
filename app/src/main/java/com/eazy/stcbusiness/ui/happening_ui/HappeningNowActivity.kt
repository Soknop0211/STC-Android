package com.eazy.stcbusiness.ui.happening_ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.base_dapter.AbsoluteFitLayoutManager
import com.eazy.stcbusiness.databinding.ActivityHappeningNowBinding
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.model.ItemImageSliderModel
import com.eazy.stcbusiness.ui.happening_ui.adapter.ImageSliderAdapter
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.ui.home.HomeContentFragment.Companion.HAPPENING_NOW_HOME
import com.eazy.stcbusiness.ui.todo_ui.adapter.TodoDestinationAdapter
import com.eazy.stcbusiness.view_model.HappeningNowViewModel
import com.eazy.stcbusiness.view_model.OnItemListener
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HappeningNowActivity : BaseActivity<ActivityHappeningNowBinding, HappeningNowViewModel>(),
    OnItemListener {

    companion object {
        fun gotoHappeningNowActivity(activity: Context){
            val intent = Intent(activity, HappeningNowActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override val layoutId: Int get() = R.layout.activity_happening_now
    override val mViewModel: HappeningNowViewModel by viewModels()

    val mListSlide = ArrayList<ItemImageSliderModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViewModel()

        initData()

    }

    private fun bindViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)

    }

    private fun initData() {
        // Init Body
        mViewModel.itemList.observe(this) {
            initRecyclerView(it)

            replaceFragment()
        }

        val imageList = listOf(
            "https://dev.booknow.asia/images/home_slider_1.jpg",
            "https://dev.booknow.asia/images/home_slider_3.jpg",
            "https://dev.booknow.asia/images/home_slider_4.jpg",
            "https://dev.booknow.asia/images/home_slider_5.jpg",
            "https://dev.booknow.asia/images/home_slider_6.jpg"
        )

        val slideModels = ArrayList(imageList)
        for(item in slideModels) {
            mListSlide.add(ItemImageSliderModel(image = item))
        }

        val mImageSliderAdapter = ImageSliderAdapter(this, mListSlide)
        mBinding.viewPagerImageSlider.adapter = mImageSliderAdapter

        val timer = Timer()
        timer.scheduleAtFixedRate(TheSlideTimer(), 2000, 3000)
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPagerImageSlider, true)

    }

    inner class TheSlideTimer : TimerTask() {
        override fun run() {
            runOnUiThread {
                if (mBinding.viewPagerImageSlider.currentItem < mListSlide.size - 1) {
                    mBinding.viewPagerImageSlider.currentItem = mBinding.viewPagerImageSlider.currentItem + 1
                } else mBinding.viewPagerImageSlider.currentItem = 0
            }
        }
    }

    private fun replaceFragment() {
        val hotelHomeListHotelFragment = HomeContentFragment.newInstance(HAPPENING_NOW_HOME)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.viewpager, hotelHomeListHotelFragment, hotelHomeListHotelFragment.tag)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    private fun initRecyclerView(mList: List<CustomCategoryModel>) {
        mBinding.recyclerView.apply {
            layoutManager =  AbsoluteFitLayoutManager(
                context,
                1,
                RecyclerView.HORIZONTAL,
                false,
                3
            )
            adapter = TodoDestinationAdapter(this@HappeningNowActivity, mList) {}
            isNestedScrollingEnabled = true
        }
    }

    override fun onSearchClick() {

    }
}