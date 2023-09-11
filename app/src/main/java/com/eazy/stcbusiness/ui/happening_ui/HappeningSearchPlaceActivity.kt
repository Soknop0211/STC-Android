package com.eazy.stcbusiness.ui.happening_ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.databinding.ActivityHappeningSearchPlaceBinding
import com.eazy.stcbusiness.model.ItemRecentSearchModel
import com.eazy.stcbusiness.ui.happening_ui.adapter.HappeningItemRecentSearchAdapter
import com.eazy.stcbusiness.view_model.HappeningSearchPlaceViewModel
import com.eazy.stcbusiness.view_model.OnListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappeningSearchPlaceActivity : BaseActivity<ActivityHappeningSearchPlaceBinding, HappeningSearchPlaceViewModel>(),
    OnListener {

    override val layoutId: Int get() = R.layout.activity_happening_search_place
    override val mViewModel: HappeningSearchPlaceViewModel by viewModels()

    companion object {
        fun gotoHappeningNowSearchPlaceActivity(activity: Context){
            val intent = Intent(activity, HappeningSearchPlaceActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)

        val list = ArrayList<ItemRecentSearchModel>()
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "10 Happening available at this location"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "10 Happening available at this location"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "10 Happening available at this location"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "10 Happening available at this location"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "10 Happening available at this location"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "10 Happening available at this location"))

        mBinding.categoryRecycle.apply {
            layoutManager = LinearLayoutManager(this@HappeningSearchPlaceActivity)
            adapter = HappeningItemRecentSearchAdapter(list) {

            }
        }
    }

    override fun onSearchClick() {

    }
}