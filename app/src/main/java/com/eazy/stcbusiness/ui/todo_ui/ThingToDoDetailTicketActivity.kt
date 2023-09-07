package com.eazy.stcbusiness.ui.todo_ui

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
import com.eazy.stcbusiness.ui.todo_ui.adapter.TicketAvailableAdapter
import com.eazy.stcbusiness.ui.todo_ui.fragment.SelectNumberPeopleBottomSheetFragment
import com.eazy.stcbusiness.ui.todo_ui.fragment.SelectStartEndDateRangBottomSheet
import com.eazy.stcbusiness.view_model.OnTicketListener
import com.eazy.stcbusiness.view_model.ThingsToDoDetailTicketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThingToDoDetailTicketActivity : BaseActivity<ActivityThingToDoDetailTicketBinding, ThingsToDoDetailTicketViewModel>(),
    OnTicketListener {

    override val layoutId: Int get() = R.layout.activity_thing_to_do_detail_ticket
    override val mViewModel: ThingsToDoDetailTicketViewModel by viewModels()

    private var mAdapter : TicketAvailableAdapter ? = null
    private var room = "0"
    private var adults = "0"
    private var children = "0"
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

        mViewModel.setContext(this)

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

    private fun startEndDateRang(){
        val selectLocationBookingFragment = SelectStartEndDateRangBottomSheet.newInstance(mViewModel.mStartDate.get() ?: "", mViewModel.mEndDate.get() ?: "")
        selectLocationBookingFragment.initListener(onCallBackDateListener)
        selectLocationBookingFragment.show(supportFragmentManager, "fragment")
    }

    private val onCallBackDateListener = object : SelectStartEndDateRangBottomSheet.OnClickCallBackListener{
        override fun onClickSelect(getStartDate: String, getEndDate : String) {
            mViewModel.mStartDate.set(getStartDate)
            mViewModel.mEndDate.set(getEndDate)
            mViewModel.setSetDate(getStartDate, getEndDate)
        }
    }

    override fun onDateSelectClick() {
        startEndDateRang()
    }

    override fun onPeopleSelectClick() {
        val selectLocationBookingFragment : SelectNumberPeopleBottomSheetFragment =
            SelectNumberPeopleBottomSheetFragment().newInstance(room, adults,children, "select_room")
        selectLocationBookingFragment.initListener(object :
            SelectNumberPeopleBottomSheetFragment.OnClickCallBackListener {
            override fun onClickSelect(titleHeader: String, r: String, ad: String, ch: String) {
                room = r
                adults = ad
                children = ch

                val totalNumber = r.toInt() + ad.toInt() + ch.toInt()

                mViewModel.mPerson.set(totalNumber.toString())
            }

        })
        selectLocationBookingFragment.show(supportFragmentManager, "fragment")
    }


}