package com.eazy.stcbusiness.ui.transportation.car_rental

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.SampleBaseBottomSheetDialogFragment
import com.eazy.stcbusiness.databinding.FragmentTransportationAskQuestionBottomSheetBinding
import com.eazy.stcbusiness.model.CarRentalRecommendModel
import com.eazy.stcbusiness.model.CarRentalSuggestedRideModel
import com.eazy.stcbusiness.ui.happening_ui.HappeningAddPeopleBottomSheetFragment
import com.eazy.stcbusiness.ui.tours_guide.TourGuideMainActivity.Companion.gotoTourGuideMainActivity
import com.eazy.stcbusiness.ui.transportation.booknow.AddNoteBottomSheetFragment
import com.eazy.stcbusiness.ui.transportation.booknow.TransportationConfirmCheckOutActivity
import com.eazy.stcbusiness.utils.listener.CustomResponseOnClickListener
import com.eazy.stcbusiness.utils.listener.CustomSetOnClickViewListener


class TransportationAskQuestionBottomSheetFragment : SampleBaseBottomSheetDialogFragment<FragmentTransportationAskQuestionBottomSheetBinding>() {

    override val layoutResource: Int = R.layout.fragment_transportation_ask_question_bottom_sheet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init Data
        val mItem : CarRentalSuggestedRideModel = getDataFromModelClass(
            CAR_RENTAL_DATA, arguments) ?: CarRentalSuggestedRideModel()

        val mItemOptionList : ArrayList<CarRentalRecommendModel> = getArgumentsList(
            ADD_OPTION_SERVICE, arguments)

        val mTotalPrice : Double = getArgumentsDouble(
            TOTAL_PRICE, arguments)

        // Init Action
        mBinding.btnYes.setOnClickListener(CustomSetOnClickViewListener (object : CustomResponseOnClickListener {
            override fun onClick(view: View) {
                gotoTourGuideMainActivity(mActivity!!)
                dismiss()
            }

        }))

        mBinding.btnSkip.setOnClickListener(CustomSetOnClickViewListener (object : CustomResponseOnClickListener {
            override fun onClick(view: View) {
                TransportationConfirmCheckOutActivity.gotoTransportationConfirmCheckOutActivity(
                    mActivity!!, mItem, mItemOptionList
                )
               dismiss()
            }

        }))

    }



    companion object {

        const val CAR_RENTAL_DATA = "CAR_RENTAL_DATA"
        const val TOTAL_PRICE = "TOTAL_PRICE"
        const val ADD_OPTION_SERVICE = "ADD_OPTION_SERVICE"

        @JvmStatic
        fun newInstance(mDataTaxi : CarRentalSuggestedRideModel,
                        mServiceOptionList: ArrayList<CarRentalRecommendModel>) =
            TransportationAskQuestionBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(CAR_RENTAL_DATA, mDataTaxi)
                    putSerializable(ADD_OPTION_SERVICE, mServiceOptionList)
                }
            }

        fun gotoAskQuestionPopupBottomSheet(supportFragmentManager : FragmentManager,
                                            mDataTaxi : CarRentalSuggestedRideModel,
                                            mServiceOptionList: ArrayList<CarRentalRecommendModel>) {
            val mDialog = newInstance(mDataTaxi, mServiceOptionList)
            mDialog.show(supportFragmentManager, TransportationAskQuestionBottomSheetFragment::class.java.name)
        }
    }


}