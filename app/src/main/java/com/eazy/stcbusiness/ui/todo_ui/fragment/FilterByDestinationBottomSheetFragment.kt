package com.eazy.stcbusiness.ui.todo_ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseBottomSheetDialogFragment
import com.eazy.stcbusiness.databinding.FragmentFilterByDestinationBottomSheetBinding
import com.eazy.stcbusiness.model.LocationModel
import com.eazy.stcbusiness.model.StarRatingModel
import com.eazy.stcbusiness.ui.todo_ui.adapter.StarRatingAdapter
import com.eazy.stcbusiness.utils.AppLOGG
import com.eazy.stcbusiness.utils.listener.CustomResponseOnClickListener
import com.eazy.stcbusiness.utils.listener.CustomSetOnClickViewListener
import com.eazy.stcbusiness.view_model.DestinationLocationViewModel
import com.eazy.stcbusiness.view_model.OnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterByDestinationBottomSheetFragment : BaseBottomSheetDialogFragment<FragmentFilterByDestinationBottomSheetBinding, DestinationLocationViewModel>(),
    OnClickListener {

    override val layoutResource: Int get() = R.layout.fragment_filter_by_destination_bottom_sheet

    override val mViewModel: DestinationLocationViewModel by viewModels()

    private var minPrice: String = ""
    private var maxPrice: String = ""
    private lateinit var onClickAllAction : DestinationLocationBottomSheetFragment.OnClickCallBackListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.bind(this)

        val mListLocation = ArrayList<LocationModel>()
        mListLocation.add(LocationModel("traveller_ranking", "Traveller Ranking"))
        mListLocation.add(LocationModel("price_low_to_high", "Price low to high"))
        mListLocation.add(LocationModel("distance_to_me", "Distance to me"))
        mListLocation.add(LocationModel("best_value", "Best Value"))

        mBinding.recyclerViewItem.apply {
            layoutManager = GridLayoutManager(mActivity, 2, LinearLayoutManager.VERTICAL,false)
            adapter = StarRatingAdapter(mListLocation, StarRatingAdapter.FILTER_ITEM,
                object : StarRatingAdapter.OnClickCallBackLister {
                    override fun onClickCallBack(value: LocationModel) {
                    }

                })
        }


        val mStarRatingModel = ArrayList<LocationModel>()
        for (i in 1..5){
            val mModel = StarRatingModel()
            mModel.id = i.toString()
            mModel.name = (6 - i).toString()
            mStarRatingModel.add(mModel)
        }

        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = StarRatingAdapter(mStarRatingModel, StarRatingAdapter.RATING_ITEM,
                object : StarRatingAdapter.OnClickCallBackLister {
                    override fun onClickCallBack(value: LocationModel) {
//                        for (mItem in mStarRatingModel) {
//                            mItem.isClicked = mItem.id == value.id
//                        }
//                        mBinding.recyclerView.adapter?.notifyDataSetChanged()
                    }

                })
        }

        if (minPrice == "" && maxPrice == ""){
            minPrice = mBinding.rangePrice.valueFrom.toString()
            maxPrice = mBinding.rangePrice.valueTo.toString()
        }

        val list = ArrayList<Float>()
        list.add(minPrice.toFloat())
        list.add(maxPrice.toFloat())
        mBinding.rangePrice.values = list

        mBinding.rangePrice.addOnChangeListener { _, _, _ ->
            val values = mBinding.rangePrice.values
            minPrice = values[0].toString()
            maxPrice = values[1].toString()
            AppLOGG.d("Loginparicerang", String.format("%s : $ %s - $ %s", "From", minPrice, maxPrice))
        }

        mBinding.applyTv.setOnClickListener (CustomSetOnClickViewListener (object : CustomResponseOnClickListener {
            override fun onClick(view: View) {
                mViewModel.onClickApply()
            }
        }))

        mBinding.resetTv.setOnClickListener (CustomSetOnClickViewListener (object : CustomResponseOnClickListener {
            override fun onClick(view: View) {
                mViewModel.onClickReset()
            }
        }))

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

    override fun onClickListener() {
        onClickAllAction.onClickSelectLocation()
        dismiss()
    }

    fun initListener(onClickAllAction: DestinationLocationBottomSheetFragment.OnClickCallBackListener) {
        this.onClickAllAction = onClickAllAction
    }

}