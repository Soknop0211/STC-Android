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
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.ui.home.HomeContentFragment.Companion.HAPPENING_NOW_HOME
import com.eazy.stcbusiness.ui.todo_ui.adapter.TodoDestinationAdapter
import com.eazy.stcbusiness.view_model.HappeningNowViewModel
import com.eazy.stcbusiness.view_model.OnItemListener
import dagger.hilt.android.AndroidEntryPoint

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViewModel()

    }

    private fun bindViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)

        mViewModel.itemList.observe(this) {
            initRecyclerView(it)

            replaceFragment()
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
            adapter = TodoDestinationAdapter(this@HappeningNowActivity, mList)
            isNestedScrollingEnabled = true
        }
    }

    override fun onSearchClick() {

    }
}