package com.eazy.stcbusiness.ui.todo_things.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.archit.calendardaterangepicker.customviews.CalendarListener
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.utils.DateUtil
import com.eazy.stcbusiness.utils.backgroundTint
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class SelectStartEndDateRangBottomSheet : BottomSheetDialogFragment() {

    private lateinit var calendar: DateRangeCalendarView
    private lateinit var mContext : Context
    private lateinit var onClickAllAction : OnClickCallBackListener
    private lateinit var btnSelectRangDate : TextView
    private var selectStartDate = ""
    private var selectEndDate = ""
    private lateinit var fmt : SimpleDateFormat

    companion object {
        fun newInstance(startDate: String, endDate: String): SelectStartEndDateRangBottomSheet {
            val bottomSheetDialogFragment = SelectStartEndDateRangBottomSheet()
            val bundle = Bundle()
            bundle.putString("start_date", startDate)
            bundle.putString("end_date", endDate)
            bottomSheetDialogFragment.arguments = bundle
            return bottomSheetDialogFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null && requireArguments().containsKey("start_date")) {
            selectStartDate = requireArguments().getString("start_date").toString()
        }
        if (arguments != null && requireArguments().containsKey("end_date")) {
            selectEndDate = requireArguments().getString("end_date").toString()
        }

        if (dialog != null) dialog!!.setCancelable(false)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_select_start_end_date_rang_bottomsheet, container, false)

        initView(view)

        initAction()

        return view
    }

    private fun initView(view : View){
        calendar = view.findViewById(R.id.calendar)
        btnSelectRangDate = view.findViewById(R.id.btnSelectRangDate)
    }

    private fun initAction(){
        fmt = DateUtil.simpleDateFormat("yyyy-MM-dd")

        setEnableBtnSelect()

        getStartEndDate()

        calendar.setCalendarListener(object : CalendarListener {
            override fun onFirstDateSelected(startDate: Calendar) {
                selectStartDate = fmt.format(startDate.time)
                selectEndDate = fmt.format(startDate.time)
                // rangDate.text = String.format("%s - %s", selectStartDate, selectEndDate)
                setEnableBtnSelect()
            }

            override fun onDateRangeSelected(startDate: Calendar, endDate: Calendar) {
                selectStartDate = fmt.format(startDate.time)
                selectEndDate = fmt.format(endDate.time)
                // rangDate.text = String.format("%s - %s", fmt.format(startDate.time), fmt.format(endDate.time))
                setEnableBtnSelect()
            }
        })

        btnSelectRangDate.setOnClickListener {
            onClickAllAction.onClickSelect(selectStartDate, selectEndDate)
            dismiss()
        }

        // rangDate.text = String.format("%s - %s", selectStartDate, selectEndDate)
        if (selectStartDate != "" && selectEndDate != "") {
            calendar.setSelectedDateRange(convertDateToCalender(selectStartDate), convertDateToCalender(selectEndDate))
        }

    }

    private fun getStartEndDate(){
        // Start Date
        val dateToday = Date()
        // Due Date
        val cal = Calendar.getInstance()
        cal.time = dateToday
        cal.add(Calendar.YEAR, 1)
        val dateNext = cal.time
        // Enable date select one year
        calendar.setSelectableDateRange(convertDateToCalender(fmt.format(dateToday)), convertDateToCalender(fmt.format(dateNext)))
    }

    private fun setEnableBtnSelect(){
        btnSelectRangDate.backgroundTint(R.color.colorAppPrimary)
        btnSelectRangDate.isEnabled = isEnableBtn()
    }

    private fun isEnableBtn() : Boolean{
        if (selectEndDate != "" && selectStartDate != ""){
            return true
        }
        return false
    }

    private fun convertDateToCalender(dateStr : String) : Calendar{
        val calendar = Calendar.getInstance()
        try {
            val dateObj = fmt.parse(dateStr)
            if (dateObj != null) {
                calendar.time = dateObj
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return calendar
    }

    fun initListener(onClickAllAction: OnClickCallBackListener) {
        this.onClickAllAction = onClickAllAction
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    interface OnClickCallBackListener {
        fun onClickSelect(getStartDate : String, getEndDate : String)
    }
}