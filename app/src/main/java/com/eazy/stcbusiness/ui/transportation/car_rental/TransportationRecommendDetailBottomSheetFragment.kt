package com.eazy.stcbusiness.ui.transportation.car_rental

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseBottomSheetDialogFragment
import com.eazy.stcbusiness.databinding.CustomTextViewLayoutBinding
import com.eazy.stcbusiness.databinding.FragmentTrasportationRecommendDetailBottomSheetBinding
import com.eazy.stcbusiness.model.CarRentalRecommendModel
import com.eazy.stcbusiness.model.SavePlaceModel
import com.eazy.stcbusiness.ui.transportation.car_rental.TransportationSummaryRentalActivity.Companion.gotoTransportationSummaryRentalActivity
import com.eazy.stcbusiness.view_model.OnClickRecommendListener
import com.eazy.stcbusiness.view_model.TransportationRecommendDetailBottomSheetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransportationRecommendDetailBottomSheetFragment : BaseBottomSheetDialogFragment<FragmentTrasportationRecommendDetailBottomSheetBinding, TransportationRecommendDetailBottomSheetViewModel>(),
    OnClickRecommendListener {

    override val layoutResource: Int get() = R.layout.fragment_trasportation_recommend_detail_bottom_sheet

    override val mViewModel: TransportationRecommendDetailBottomSheetViewModel by viewModels()

    companion object {
        @JvmStatic
        fun newInstance(mCarRentalRecommendModel : CarRentalRecommendModel) =
            TransportationRecommendDetailBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(DATA, mCarRentalRecommendModel)
                }
            }

        fun gotoTransportationRecommend(supportFragmentManager : FragmentManager, mCarRentalRecommendModel : CarRentalRecommendModel) {
            val mTransportationRecommendDetailBottomSheetFragment = newInstance(mCarRentalRecommendModel)
            mTransportationRecommendDetailBottomSheetFragment.show(supportFragmentManager, TransportationRecommendDetailBottomSheetFragment::class.java.name)
        }

        const val DATA = "DATA"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

    }

    private fun initViewModel() {
        mViewModel.bind(this)

        setVariable(BR.viewModel, mViewModel)

        val mItem : CarRentalRecommendModel = getDataFromModelClass(DATA, arguments) ?: CarRentalRecommendModel()
        mViewModel.initData(mItem)

        // feature list
        mViewModel.initListFeature()
        mViewModel.itemList.observe(this) { mList ->
            mList.forEach {
                addFeature(it, mActivity!!)
            }
        }

    }

    private fun addFeature(mSt : String, mContext: Context){
        val binding: CustomTextViewLayoutBinding = CustomTextViewLayoutBinding
            .inflate(LayoutInflater.from(mContext))
        binding.txt.text = mSt
        val lp = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(10, 10, 10, 10)
        binding.mainLayout.layoutParams = lp
        mBinding.peopleAdded.addView(binding.root)
    }


    override fun onDismiss() {

    }

    override fun onClickCallBack() {
        gotoTransportationSummaryRentalActivity(mActivity!!)
    }
}