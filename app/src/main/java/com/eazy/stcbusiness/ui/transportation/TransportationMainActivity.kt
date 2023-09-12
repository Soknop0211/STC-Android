package com.eazy.stcbusiness.ui.transportation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.base_dapter.MainHomeShowItemAdapter
import com.eazy.stcbusiness.databinding.ActivityHappeningNowBinding
import com.eazy.stcbusiness.databinding.ActivityTransportationMainBinding
import com.eazy.stcbusiness.model.CustomCategoryDataList
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.ui.happening_ui.HappeningEventUpActivity
import com.eazy.stcbusiness.ui.happening_ui.HappeningNowDetailActivity
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.ui.todo_ui.ThingToDoDetailActivity
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import com.eazy.stcbusiness.view_model.HappeningNowViewModel
import com.eazy.stcbusiness.view_model.OnItemListener
import com.eazy.stcbusiness.view_model.TransportationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransportationMainActivity : BaseActivity<ActivityTransportationMainBinding, TransportationViewModel>(),
    OnClickCallBackListener {

    companion object {
        fun gotoTransportationMainActivity(activity: Context){
            val intent = Intent(activity, TransportationMainActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override val layoutId: Int get() = R.layout.activity_transportation_main

    override val mViewModel: TransportationViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        mViewModel.itemList.observe(this) {
            mBinding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@TransportationMainActivity)
                adapter = MainHomeShowItemAdapter(it , this@TransportationMainActivity, {
                }, { mModel, mString -> // On Item Click
                    gotoTransportationMainActivity(this@TransportationMainActivity)
                } ) }
        }

    }


    private fun initViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)

        mViewModel.initList(resources)
    }

    override fun onClickCallBack() {

    }
}