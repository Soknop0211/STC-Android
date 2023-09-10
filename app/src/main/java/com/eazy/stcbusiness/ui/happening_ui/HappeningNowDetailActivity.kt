package com.eazy.stcbusiness.ui.happening_ui

import android.os.Bundle
import androidx.activity.viewModels
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.databinding.ActivityHappeningNowDetailBinding
import com.eazy.stcbusiness.view_model.HappeningNowDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappeningNowDetailActivity : BaseActivity<ActivityHappeningNowDetailBinding, HappeningNowDetailViewModel>(),
    BaseView {
    override val layoutId: Int = R.layout.activity_happening_now_detail
    override val mViewModel: HappeningNowDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.bind(this)
    }
}