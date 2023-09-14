package com.eazy.stcbusiness.ui.transportation.booknow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseBottomSheetDialogFragment
import com.eazy.stcbusiness.databinding.FragmentTransportationSelectTypeBottomSheetBinding
import com.eazy.stcbusiness.model.TransportationTypeModel
import com.eazy.stcbusiness.ui.transportation.adapter.TransportationTypeAdapter
import com.eazy.stcbusiness.view_model.OnClickConfirmItemType
import com.eazy.stcbusiness.view_model.TransportationSelectTypeBottomSheetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransportationSelectTypeBottomSheetFragment : BaseBottomSheetDialogFragment<FragmentTransportationSelectTypeBottomSheetBinding, TransportationSelectTypeBottomSheetViewModel>(),
    OnClickConfirmItemType {

    override val layoutResource = R.layout.fragment_transportation_select_type_bottom_sheet
    override val mViewModel: TransportationSelectTypeBottomSheetViewModel by viewModels()

    private var mOnClickCallBackListener : OnClickCallBackListener?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        // Saved Place
        mViewModel.initList()
        mViewModel.itemList.observe(this) {
            mBinding.recyclerView.apply {
                layoutManager = LinearLayoutManager(mActivity)
                adapter = TransportationTypeAdapter(it) {
                    mViewModel.getTransportationTypeModel(it)
                }
            }
        }

    }

    private fun initViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(mActivity!!)

        setVariable(BR.viewModel, mViewModel)
    }

    override fun onDismiss() {
        dismiss()
    }


    override fun onClickCallBack() {
        if (mOnClickCallBackListener != null && mViewModel.getTransportationTypeModel() != null) {
            mOnClickCallBackListener?.onCallBackItemListener(mViewModel.getTransportationTypeModel()!!)
            dismiss()
        }
    }

    fun initListener(mOnClickCallBackListener: OnClickCallBackListener) {
        this.mOnClickCallBackListener = mOnClickCallBackListener
    }

    interface OnClickCallBackListener {
        fun onCallBackItemListener(mTransportationTypeModel : TransportationTypeModel)
    }
}