package com.eazy.stcbusiness.ui.happening_ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseBottomSheetDialogFragment
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.databinding.FragmentHappeningAddPeopleBottomSheetBinding
import com.eazy.stcbusiness.view_model.AddPeopleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappeningAddPeopleBottomSheetFragment :
    BaseBottomSheetDialogFragment<FragmentHappeningAddPeopleBottomSheetBinding, AddPeopleViewModel>(),
    BaseView {

    override val layoutResource = R.layout.fragment_happening_add_people_bottom_sheet
    override val mViewModel: AddPeopleViewModel by viewModels()
    private lateinit var onClickAllAction : OnClickCallBackListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.bind(this)
    }


    fun initListener(onClickAllAction: OnClickCallBackListener) {
        this.onClickAllAction = onClickAllAction
    }

    interface OnClickCallBackListener {
        fun onClickSelectLocation()
    }

}