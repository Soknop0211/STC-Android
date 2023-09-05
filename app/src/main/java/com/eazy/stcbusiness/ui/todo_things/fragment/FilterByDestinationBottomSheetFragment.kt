package com.eazy.stcbusiness.ui.todo_things.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.getBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseBottomSheetDialogFragment
import com.eazy.stcbusiness.databinding.FragmentFilterByDestinationBottomSheetBinding
import com.eazy.stcbusiness.databinding.ListConciergeFilterProductCriteriaItemBinding
import com.eazy.stcbusiness.model.LocationModel
import com.eazy.stcbusiness.view_model.DestinationLocationViewModel
import com.eazy.stcbusiness.view_model.FilterByDestinationViewModel

class FilterByDestinationBottomSheetFragment : BaseBottomSheetDialogFragment<FragmentFilterByDestinationBottomSheetBinding, DestinationLocationViewModel>()  {

    override val layoutResource: Int get() = R.layout.fragment_filter_by_destination_bottom_sheet

    override val mViewModel: DestinationLocationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // mViewModel.bind(this)

        val mListLocation = ArrayList<LocationModel>()
        for (i in 1..8){
            mListLocation.add(LocationModel(i.toString(), "Location $i"))
        }

        for (mItem in mListLocation) {
            val binding: ListConciergeFilterProductCriteriaItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(requireContext()),
                R.layout.list_concierge_filter_product_criteria_item,
                mBinding.flowLayout,
                true
            )
            val viewModel =
                FilterByDestinationViewModel("", mItem)
            binding.setVariable(BR.viewModel, viewModel)
        }


//        val list = ArrayList<Float>()
//        list.add(minPrice.toFloat())
//        list.add(maxPrice.toFloat())
//        mBinding.rangePrice.values = list
//
//        mBinding.rangePriceTv.text = String.format("%s : $ %s - $ %s", "From", minPrice, maxPrice)
//        mBinding.rangePrice.addOnChangeListener { _, _, _ ->
//            val values = mBinding.rangePrice.values
//            minPrice = values[0].toString()
//            maxPrice = values[1].toString()
//            mBinding.rangePriceTv.text = String.format("%s : $ %s - $ %s", "From", minPrice, maxPrice)
//            onClickCallBack!!.onAlertRangePrice(minPrice, maxPrice)
//        }


    }

    companion object {

        private const val LOCATION_ID = "LOCATION_ID"

        @JvmStatic
        fun newInstance(mLocationId : String) =
            FilterByDestinationBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString(LOCATION_ID, mLocationId)
                }
            }
    }
}