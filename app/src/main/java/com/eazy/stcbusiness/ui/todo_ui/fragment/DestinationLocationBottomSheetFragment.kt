package com.eazy.stcbusiness.ui.todo_ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseBottomSheetDialogFragment
import com.eazy.stcbusiness.databinding.FragmentDestinationLocationBottomSheetBinding
import com.eazy.stcbusiness.view_model.DestinationLocationViewModel
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.model.LocationModel
import com.eazy.stcbusiness.ui.todo_ui.adapter.SelectLocationAdapter
import com.eazy.stcbusiness.view_model.OnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class DestinationLocationBottomSheetFragment :
    BaseBottomSheetDialogFragment<FragmentDestinationLocationBottomSheetBinding, DestinationLocationViewModel>(),
    OnClickListener {

    override val layoutResource: Int get() = R.layout.fragment_destination_location_bottom_sheet
    override val mViewModel: DestinationLocationViewModel by viewModels()


    private val mId : String? = null
    private lateinit var onClickAllAction : OnClickCallBackListener

    companion object {

        private const val ACTION = "ACTION"
        const val ONLY_DESTINATION = "ONLY_DESTINATION"

        @JvmStatic
        fun newInstance(mId : String) =
            DestinationLocationBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString(ACTION, mId)
                }
            }

        fun gotoDestinationLocationBottomSheetFragment(supportFragmentManager : FragmentManager, onClickAllAction: OnClickCallBackListener, mAction : String) {
            val mDestinationBottomSheet = newInstance(mAction)
            mDestinationBottomSheet.initListener(onClickAllAction)
            mDestinationBottomSheet.show(supportFragmentManager, DestinationLocationBottomSheetFragment::class.java.name)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.bind(this)

        val mListLocation = ArrayList<LocationModel>()
        mListLocation.add(LocationModel("1", "Siem Reap, Cambodia"))
        mListLocation.add(LocationModel("1", "Battam Bong"))
        mListLocation.add(LocationModel("1", "Sihanoukville, Cambodia"))
        mListLocation.add(LocationModel("1", "Kampot, Cambodia"))
        mListLocation.add(LocationModel("1", "Kep, Cambodia"))
        mListLocation.add(LocationModel("1", "Koh Kong, Cambodia"))
        mListLocation.add(LocationModel("1", "Mondulkiri, Cambodia"))
        mListLocation.add(LocationModel("1", "Banteay Meanchey, Cambodia"))
        mListLocation.add(LocationModel("1", "Kompng Cham, Cambodia"))
        mListLocation.add(LocationModel("1", "Kampong Speu, Cambodia"))
        mListLocation.add(LocationModel("1", "Kompong Thom, Cambodia"))

        val mAction = getArgumentsString(ACTION, arguments)

        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(mActivity)
            adapter = SelectLocationAdapter(mId ?: "", mListLocation,
                object : SelectLocationAdapter.OnClickCallBackLister {
                    override fun onClickCallBack(value: LocationModel) {
                        if (!TextUtils.isEmpty(mAction) && mAction == ONLY_DESTINATION) {
                             onClickAllAction.onClickSelectLocation(value)
                             dismiss()
                        } else {
                            val mDestinationBottomSheet = FilterByDestinationBottomSheetFragment.newInstance("")
                            mDestinationBottomSheet.initListener(object : FilterByDestinationBottomSheetFragment.OnClickCallBackListener {
                                override fun onClickSelectLocation() {
                                    dismiss()
                                }

                            })
                            mDestinationBottomSheet.show(childFragmentManager, mDestinationBottomSheet::class.java.name)
                        }
                    }

                })
        }

        setVariable(BR.viewModel, mViewModel)
    }

    fun initListener(onClickAllAction: OnClickCallBackListener) {
        this.onClickAllAction = onClickAllAction
    }

    interface OnClickCallBackListener {
        fun onClickSelectLocation(mLocationModel : LocationModel)
    }

    override fun onClickListener() {

    }
}