package com.eazy.stcbusiness.ui.transportation.bookschedule

import android.os.Bundle
import android.view.View
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.SampleBaseBottomSheetDialogFragment
import com.eazy.stcbusiness.databinding.FragmentDateBottomSheetDialogBinding
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker
import java.util.Date

class DateBottomSheetDialogFragment : SampleBaseBottomSheetDialogFragment<FragmentDateBottomSheetDialogBinding>() {

    private lateinit var selectedDateListener : SelectDateListener
    private var currentDate : Date?  = null
    private var title : String? = null
    private lateinit var selectDatePicker: SingleDateAndTimePicker

    override val layoutResource: Int get() = R.layout.fragment_date_bottom_sheet_dialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        initAction()
    }

    private fun initView(){
        selectDatePicker = mBinding.selectDateTimePicker
        selectDatePicker.setDefaultDate(currentDate)
        selectDatePicker.setDisplayDays(true)
        selectDatePicker.setDisplayHours(true)
        selectDatePicker.setDisplayMinutes(true)
        selectDatePicker.setIsAmPm(true)
    }

    companion object {
        @JvmStatic
        fun newInstance(
            currentDate : Date, selectDateListener: SelectDateListener) =
            DateBottomSheetDialogFragment().apply {
                this.currentDate = currentDate
                this.selectedDateListener = selectDateListener
            }
    }

    private fun initAction(){
        mBinding.iconBack.setOnClickListener { dismiss() }

        mBinding.btnConfirm.setOnClickListener {
            selectedDateListener.selectDateListener(selectDatePicker.date)
            dismiss()
        }
    }

    interface SelectDateListener {
        fun selectDateListener(date: Date)
    }
}