package com.eazy.stcbusiness.ui.todo_things

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.databinding.ActivityThingToDoDetailTicketBinding
import com.eazy.stcbusiness.model.TicketAvailableModel
import com.eazy.stcbusiness.ui.todo_things.adapter.TicketAvailableAdapter
import com.eazy.stcbusiness.view_model.ThingsToDoDetailTicketViewModel
import com.eazy.stcbusiness.view_model.ThingsToDoDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThingToDoDetailTicketActivity : BaseActivity<ActivityThingToDoDetailTicketBinding, ThingsToDoDetailTicketViewModel>() {

    override val layoutId: Int get() = R.layout.activity_thing_to_do_detail_ticket
    override val mViewModel: ThingsToDoDetailTicketViewModel by viewModels()

    private var mAdapter : TicketAvailableAdapter ? = null

    companion object {
        fun gotoSearchDestinationThingToDoTicketActivity(activity: Context){
            val intent = Intent(activity, ThingToDoDetailTicketActivity::class.java)
            activity.startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel.bind(this)

        setVariable(BR.viewModel, mViewModel)

        initAction()
    }

    private fun initAction() {
        val mListTicket = ArrayList<TicketAvailableModel>()
        for (i in 1..20){
            val mModel = TicketAvailableModel("Half Day-Premium",
                "Best seller", "Siem Reap is a cheerful city that embraces travelers like old friends.",
                "3:00 PM", "4:45 PM", (10 + i.toDouble())
            )
            mModel.id = i.toString()
            mListTicket.add(mModel)
        }

        mAdapter = TicketAvailableAdapter(mListTicket) {
            mViewModel.setPriceTotal(mAdapter?.updateProductOrderCounter(it?.id ?: "") ?: 0.00, this)
        }

        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ThingToDoDetailTicketActivity)
            adapter = mAdapter
        }

        mViewModel.setPriceTotal(mAdapter?.updateProductOrderCounter(mListTicket[0].id ?: "") ?: 0.00, this)
    }

}