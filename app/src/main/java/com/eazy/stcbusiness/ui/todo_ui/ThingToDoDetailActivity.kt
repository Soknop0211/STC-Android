package com.eazy.stcbusiness.ui.todo_ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.databinding.ActivityThingToDoDetailBinding
import com.eazy.stcbusiness.ui.todo_ui.ThingToDoDetailTicketActivity.Companion.gotoSearchDestinationThingToDoTicketActivity
import com.eazy.stcbusiness.view_model.OnDetailButtonListener
import com.eazy.stcbusiness.view_model.ThingsToDoDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThingToDoDetailActivity : BaseActivity<ActivityThingToDoDetailBinding, ThingsToDoDetailViewModel>(),
    OnDetailButtonListener {

    override val layoutId: Int get() = R.layout.activity_thing_to_do_detail
    override val mViewModel: ThingsToDoDetailViewModel by viewModels()

    companion object {
        fun gotoSearchDestinationThingToDoActivity(activity: Context){
            val intent = Intent(activity, ThingToDoDetailActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.bind(this)
        mViewModel.setContext(this)

        mViewModel.setPriceTotal(8.00, this)

        setVariable(BR.viewModel, mViewModel)
    }

    override fun onBookNowClick() {
        gotoSearchDestinationThingToDoTicketActivity(this)
    }

}