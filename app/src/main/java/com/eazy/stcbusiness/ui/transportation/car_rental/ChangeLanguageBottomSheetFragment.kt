package com.eazy.stcbusiness.ui.transportation.car_rental

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.SampleBaseBottomSheetDialogFragment
import com.eazy.stcbusiness.databinding.FragmentDestinationLocationBottomSheetBinding
import com.eazy.stcbusiness.model.LanguageModel
import com.eazy.stcbusiness.model.LocationModel
import com.eazy.stcbusiness.ui.todo_ui.adapter.SelectLocationAdapter

class ChangeLanguageBottomSheetFragment : SampleBaseBottomSheetDialogFragment<FragmentDestinationLocationBottomSheetBinding>() {

    override val layoutResource = R.layout.fragment_destination_location_bottom_sheet

    private lateinit var onClickAllAction : OnClickCallBackListener

    companion object {
        fun gotoChangeLanguageBottomSheetFragment(supportFragmentManager : FragmentManager,
                                                  mLangList: ArrayList<LanguageModel>,
                                                  onClickAllAction: OnClickCallBackListener) {
            val mDestinationBottomSheet = newInstance(mLangList)
            mDestinationBottomSheet.initListener(onClickAllAction)
            mDestinationBottomSheet.show(supportFragmentManager, ChangeLanguageBottomSheetFragment::class.java.name)
        }

        @JvmStatic
        fun newInstance(mLangList: ArrayList<LanguageModel>) =
            ChangeLanguageBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(LANGUAGE_LIST, mLangList)
                }
            }


        const val LANGUAGE_LIST = "LANGUAGE_LIST"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mItemLangList : ArrayList<LanguageModel> = getArgumentsList(LANGUAGE_LIST, arguments)
        val mId = ""
        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(mActivity)
            adapter = SelectLocationAdapter(mId, mItemLangList,
                object : SelectLocationAdapter.OnClickCallBackLister {
                    override fun onClickCallBack(value: LocationModel) {
                        onClickAllAction.onClickSelectLocation(value)
                        dismiss()
                    }

                })
        }

    }


    fun initListener(onClickAllAction: OnClickCallBackListener) {
        this.onClickAllAction = onClickAllAction
    }

    interface OnClickCallBackListener {
        fun onClickSelectLocation(mLocationModel : LocationModel)
    }
}