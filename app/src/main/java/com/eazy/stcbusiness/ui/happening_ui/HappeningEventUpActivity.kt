package com.eazy.stcbusiness.ui.happening_ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.base_dapter.MainHomeShowItemAdapter
import com.eazy.stcbusiness.databinding.ActivityHappeningEventUpBinding
import com.eazy.stcbusiness.model.CustomCategoryDataList
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.model.ItemCategoryDateModel
import com.eazy.stcbusiness.ui.happening_ui.HappeningSearchPlaceActivity.Companion.gotoHappeningNowSearchPlaceActivity
import com.eazy.stcbusiness.ui.happening_ui.adapter.EventUpComingAdapter
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.view_model.HappeningEventUpViewModel
import com.eazy.stcbusiness.view_model.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappeningEventUpActivity : BaseActivity<ActivityHappeningEventUpBinding, HappeningEventUpViewModel>(),
    OnItemClickListener {

    companion object {
        fun gotoHappeningNowEventUpActivity(activity: Context){
            val intent = Intent(activity, HappeningEventUpActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override val layoutId: Int = R.layout.activity_happening_event_up
    override val mViewModel: HappeningEventUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViewModel()

        val list : ArrayList<ItemCategoryDateModel> = ArrayList()
        list.add(ItemCategoryDateModel("sat", "Sat", "1"))
        list.add(ItemCategoryDateModel("sun", "Sun", "1"))
        list.add(ItemCategoryDateModel("mon", "Mon", "1"))
        list.add(ItemCategoryDateModel("tue", "Tue", "1"))
        list.add(ItemCategoryDateModel("wed", "Wed", "1"))
        list.add(ItemCategoryDateModel("thu", "Thu", "1"))
        list.add(ItemCategoryDateModel("fri", "Fri", "1"))

        mBinding.categoryRecycle.apply {
            layoutManager = LinearLayoutManager(this@HappeningEventUpActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = EventUpComingAdapter(list)
        }

        mBinding.itemRecycle.apply {
            layoutManager = LinearLayoutManager(this@HappeningEventUpActivity)
            adapter = MainHomeShowItemAdapter(initList() , this@HappeningEventUpActivity, {

            })  { mModel, mString ->
                HappeningNowDetailActivity.gotoHappeningNowDetailActivity(this@HappeningEventUpActivity)
            }
        }
    }

    private fun initList() : ArrayList<CustomCategoryDataList> {
        val mList = ArrayList<CustomCategoryDataList>()

        val mListSubList = ArrayList<CustomCategoryModel>()
        mListSubList.add(CustomCategoryModel(HomeContentFragment.HAPPENING_NOW_HOME, "Phnom Penh", "10 Activities", "https://dev.booknow.asia/images/home_slider_1.jpg"))
        mListSubList.add(CustomCategoryModel(HomeContentFragment.HAPPENING_NOW_HOME, "Siem Reap", "11 Activities", "https://dev.booknow.asia/images/home_slider_2.jpg"))
        mListSubList.add(CustomCategoryModel(HomeContentFragment.HAPPENING_NOW_HOME, "Kep, Province","11 Activities", "https://dev.booknow.asia/images/home_slider_3.jpg"))
        mListSubList.add(CustomCategoryModel(HomeContentFragment.HAPPENING_NOW_HOME, "Takeo", "10 Activities", "https://dev.booknow.asia/images/home_slider_4.jpg"))
        mListSubList.add(CustomCategoryModel(HomeContentFragment.HAPPENING_NOW_HOME, "Kompong Spie", "11 Activities", "https://dev.booknow.asia/images/home_slider_5.jpg"))
        mListSubList.add(CustomCategoryModel(HomeContentFragment.HAPPENING_NOW_HOME, "Kompot","11 Activities", "https://dev.booknow.asia/images/home_slider_6.jpg"))
        mListSubList.add(CustomCategoryModel(HomeContentFragment.HAPPENING_NOW_HOME, "Kompot","11 Activities", "https://dev.booknow.asia/images/home_slider_6.jpg"))
        mList.add(CustomCategoryDataList(HomeContentFragment.HAPPENING_NOW_HOME, resources.getString(R.string.popular_upcoming_event), mListSubList, false))

        return mList
    }


    private fun bindViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)

    }

    override fun onFilterClick() {
        /*** val mDestinationBottomSheet = FilterByDestinationBottomSheetFragment.newInstance("")
        mDestinationBottomSheet.initListener(object :
            DestinationLocationBottomSheetFragment.OnClickCallBackListener {
            override fun onClickSelectLocation() {

            }

        })
        mDestinationBottomSheet.show(supportFragmentManager, mDestinationBottomSheet::class.java.name) ***/

        gotoHappeningNowSearchPlaceActivity(this)

    }
}