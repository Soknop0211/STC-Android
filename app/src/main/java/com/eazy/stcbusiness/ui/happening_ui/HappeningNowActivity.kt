package com.eazy.stcbusiness.ui.happening_ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.base_dapter.AbsoluteFitLayoutManager
import com.eazy.stcbusiness.databinding.ActivityHappeningNowBinding
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.model.ItemImageSliderModel
import com.eazy.stcbusiness.ui.happening_ui.HappeningEventUpActivity.Companion.gotoHappeningNowEventUpActivity
import com.eazy.stcbusiness.ui.happening_ui.adapter.AdapterImageSlider
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.ui.home.HomeContentFragment.Companion.HAPPENING_NOW_HOME
import com.eazy.stcbusiness.ui.todo_ui.adapter.TodoDestinationAdapter
import com.eazy.stcbusiness.view_model.HappeningNowViewModel
import com.eazy.stcbusiness.view_model.OnItemListener
import com.google.android.material.tabs.TabLayoutMediator
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

    private val sliderHandler: Handler = Handler(Looper.getMainLooper())

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

        //Slider Image
        // initImageSlideViewPager(15, mBinding.viewPagerImageSlider, sliderHandler, sliderRunnable, mListSlide)

//
//        val itemsPager_adapter = The_Slide_items_Pager_Adapter(this, mListSlide)
//        mBinding.viewPagerImageSlider.setAdapter(itemsPager_adapter)
//
//        // The_slide_timer
//        // The_slide_timer
//        val timer = Timer()
//        timer.scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                if (mBinding.viewPagerImageSlider.getCurrentItem()< mListSlide.size-1) {
//                    mBinding.viewPagerImageSlider.setCurrentItem(mBinding.viewPagerImageSlider.getCurrentItem()+1);
//                }
//                else
//                    mBinding.viewPagerImageSlider.setCurrentItem(0);
//            }
//
//        }, 2000, 3000)
//
//        mBinding.tabLayout.setupWithViewPager(mBinding.viewPagerImageSlider, true)

        val itemsPager_adapter = The_Slide_items_Pager_Adapter(this, mListSlide)
        mBinding.viewPagerImageSlider.setAdapter(itemsPager_adapter)

        // The_slide_timer

        // The_slide_timer
        val timer = Timer()
        timer.scheduleAtFixedRate(The_slide_timer(), 2000, 3000)

        mBinding.tabLayout.setupWithViewPager(mBinding.viewPagerImageSlider, true)

    }

    inner class The_slide_timer : TimerTask() {
        override fun run() {
            runOnUiThread {
                if (mBinding.viewPagerImageSlider.getCurrentItem() < mListSlide.size - 1) {
                    mBinding.viewPagerImageSlider.setCurrentItem(mBinding.viewPagerImageSlider.getCurrentItem() + 1)
                } else mBinding.viewPagerImageSlider.setCurrentItem(0)
            }
        }
    }

    private fun initImageSlideViewPager(startEndPadding : Int, viewPager2 : ViewPager2, sliderHandler: Handler, sliderRunnable : Runnable, imageModelList: MutableList<ItemImageSliderModel>){
        val imageList: ArrayList<ItemImageSliderModel> = ArrayList()
        imageList.addAll(imageModelList)

        val mAdapter = AdapterImageSlider(imageList, viewPager2)
        viewPager2.setPadding(startEndPadding, 0, startEndPadding, 0)
        viewPager2.adapter = mAdapter
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))

        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        viewPager2.setPageTransformer(compositePageTransformer)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)// slide duration 3 seconds
            }
        })

        mAdapter.setDotCount(imageModelList.size)

        TabLayoutMediator(mBinding.tabLayout, viewPager2) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()

//        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                highlightDot(position)
//            }
//        })
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
            adapter = TodoDestinationAdapter(this@HappeningNowActivity, mList)
            isNestedScrollingEnabled = true
        }
    }

    private val sliderRunnable = Runnable { mBinding.viewPagerImageSlider.currentItem = mBinding.viewPagerImageSlider.currentItem + 1 }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 2000)
    }

    override fun onSearchClick() {

    }
}