package com.eazy.stcbusiness.ui.happening_ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.viewModels
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseBottomSheetDialogFragment
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.databinding.FragmentHappeningAddPeopleBottomSheetBinding
import com.eazy.stcbusiness.model.UserPeopleModel
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import com.eazy.stcbusiness.view_model.AddPeopleViewModel
import com.eazy.stcbusiness.view_model.OnButtonListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HappeningAddPeopleBottomSheetFragment :
    BaseBottomSheetDialogFragment<FragmentHappeningAddPeopleBottomSheetBinding, AddPeopleViewModel>(),
    OnButtonListener {

    override val layoutResource = R.layout.fragment_happening_add_people_bottom_sheet
    override val mViewModel: AddPeopleViewModel by viewModels()
    private var onClickAllAction : OnClickCallBackListener ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.bind(this)

        if(dialog != null &&  dialog?.window != null) {
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }

    }


    fun initListener(onClickAllAction: OnClickCallBackListener) {
        this.onClickAllAction = onClickAllAction
    }

    interface OnClickCallBackListener {
        fun onClickSelectLocation(mUserPeopleModel : UserPeopleModel)
    }

    override fun onClickCallBack(model: UserPeopleModel) {
        if (onClickAllAction != null) {
            onClickAllAction?.onClickSelectLocation(model)
        }
    }

}