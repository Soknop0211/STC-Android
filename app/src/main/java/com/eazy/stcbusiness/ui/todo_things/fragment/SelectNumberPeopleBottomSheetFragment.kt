package com.eazy.stcbusiness.ui.todo_things.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseBottomSheetDialogFragment
import com.eazy.stcbusiness.databinding.SelectNumberOfPeopleLayoutBinding
import com.eazy.stcbusiness.model.NumRoomBookingModel
import com.eazy.stcbusiness.ui.todo_things.adapter.SelectNumRoomBookingAdapter
import com.eazy.stcbusiness.view_model.DestinationLocationViewModel
import com.eazy.stcbusiness.view_model.OnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectNumberPeopleBottomSheetFragment : BaseBottomSheetDialogFragment<SelectNumberOfPeopleLayoutBinding, DestinationLocationViewModel>(),
    OnClickListener {

    //Select location
    private lateinit var onClickAllAction : OnClickCallBackListener
    private var mContext: Context? = null
    private var selectedItem = ""
    private var action : String = ""

    //Select room
    private var listNumBookRoom: ArrayList<NumRoomBookingModel> = ArrayList()
    private lateinit var selectNumRoomBookingAdapter : SelectNumRoomBookingAdapter
    private var room = ""
    private var adult = ""
    private var children = ""
    private var maxPeople = 0

    fun newInstance(room: String, adult: String, children: String, action : String): SelectNumberPeopleBottomSheetFragment {
        val bottomSheetDialogFragment = SelectNumberPeopleBottomSheetFragment()
        val bundle = Bundle()
        bundle.putString("room", room)
        bundle.putString("adult", adult)
        bundle.putString("children", children)
        bundle.putString("action", action)
        bottomSheetDialogFragment.arguments = bundle
        return bottomSheetDialogFragment
    }

    // For space
    fun newInstance(room: String, adult: String, children: String, maxPeople : Int, action : String): SelectNumberPeopleBottomSheetFragment {
        val bottomSheetDialogFragment = SelectNumberPeopleBottomSheetFragment()
        val bundle = Bundle()
        bundle.putString("room", room)
        bundle.putString("adult", adult)
        bundle.putString("children", children)
        bundle.putString("action", action)
        bundle.putInt("max_people", maxPeople)
        bottomSheetDialogFragment.arguments = bundle
        return bottomSheetDialogFragment
    }

    override val layoutResource: Int = R.layout.select_number_of_people_layout
    override val mViewModel: DestinationLocationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setStyle(STYLE_NO_FRAME, R.style.AlertTheme)
        if (arguments != null && requireArguments().containsKey("order_id")) {
            selectedItem = requireArguments().getString("order_id").toString()
        }
        if (arguments != null && requireArguments().containsKey("action")) {
            action = requireArguments().getString("action").toString()
        }
        if (arguments != null && requireArguments().containsKey("room")) {
            room = requireArguments().getString("room").toString()
        }
        if (arguments != null && requireArguments().containsKey("adult")) {
            adult = requireArguments().getString("adult").toString()
        }
        if (arguments != null && requireArguments().containsKey("children")) {
            children = requireArguments().getString("children").toString()
        }
        if (arguments != null && requireArguments().containsKey("max_people")) {
            maxPeople = requireArguments().getInt("max_people")
        }

        setStyle(STYLE_NO_FRAME, R.style.CustomBottomSheetDialogFragment)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.bind(this)

        mBinding.bottomLayout.btnBookNow.text = mContext?.getString(R.string.confirm)

        initAction()

        setVariable(BR.viewModel, mViewModel)
    }

    private fun initAction(){
        selectNumRoomBookingAdapter = SelectNumRoomBookingAdapter(mContext!!, addListNumRoomDefault(), onClickCallBack)
        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(mContext)
            adapter = selectNumRoomBookingAdapter
        }

        // if (maxPeople > 0) titleItem.append(String.format("%s %s %s %s", " (", mContext!!.getString(R.string.max_people), maxPeople, ")"))
    }

    private fun setBackItemRoom(){
        var getValueGuest = ""
        var room = ""
        var adults = ""
        var children = ""
        for (item in listNumBookRoom){
            getValueGuest += item.numValues + " " + item.action + if (item.action == "children") "" else " ∙ "
            when (item.action) {
                "room" -> {
                    room = item.numValues!!
                }
                "adults" -> {
                    adults = item.numValues!!
                }
                else -> {
                    children = item.numValues!!
                }
            }
        }
        onClickAllAction.onClickSelect(getValueGuest, room, adults, children)

        dismiss()
    }

    fun initListener(onClickAllAction: OnClickCallBackListener) {
        this.onClickAllAction = onClickAllAction
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    interface OnClickCallBackListener {
        fun onClickSelect(titleHeader : String, r : String, ad : String, ch : String)
    }

    // ============== Select Action Num Room ===========================
    private fun addListNumRoomDefault(): ArrayList<NumRoomBookingModel> {
        listNumBookRoom = ArrayList()
        if (room != "no_room_check")    listNumBookRoom.add(NumRoomBookingModel(if(room != "") room else "1", "room"))  //no add when not search room
        listNumBookRoom.add(NumRoomBookingModel(if(adult != "") adult else "1", "adults"))
        listNumBookRoom.add(NumRoomBookingModel(if(children != "") children else "0", "children"))
        return listNumBookRoom
    }

    private var countRoom = 0

    private val onClickCallBack = object : SelectNumRoomBookingAdapter.OnClickCallBackLister{
        @SuppressLint("NotifyDataSetChanged")
        override fun onClickCallBack(value: NumRoomBookingModel, actionBtn : String) {
            if (maxPeople > 0 && actionBtn == "add"){
                if (maxPeople == validatePeople()) return
            }
            countRoom = value.numValues!!.toInt()
            if (actionBtn == "add"){
                countRoom = countRoom.plus(1)
            } else {
                if (countRoom > 0) {
                    countRoom = countRoom.minus(1)
                }
            }
            value.numValues = countRoom.toString()
            selectNumRoomBookingAdapter.notifyDataSetChanged()
        }

    }

    private fun validatePeople() : Int {
        var max = 0
        for (item in listNumBookRoom){
            max += item.numValues!!.toInt()
        }
        return max
    }

    override fun onClickListener() {
        setBackItemRoom()
    }

}