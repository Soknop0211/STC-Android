package com.eazy.stcbusiness.ui.tours_guide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.SampleBaseFragment
import com.eazy.stcbusiness.base.SampleBaseFragment_
import com.eazy.stcbusiness.databinding.FragmentTourGuideItemDetailBinding
import com.eazy.stcbusiness.databinding.FragmentTourGuideItemListDetailBinding
import com.eazy.stcbusiness.model.CarRentalRecommendModel
import com.eazy.stcbusiness.model.TourItemServiceModel
import com.eazy.stcbusiness.ui.tours_guide.adapter.TourGuideItemServiceAdapter
import com.eazy.stcbusiness.ui.transportation.adapter.TaxiServiceOptionItemAdapter
import com.eazy.stcbusiness.utils.Utils


class TourGuideItemServiceFragment : SampleBaseFragment_<FragmentTourGuideItemListDetailBinding>() {

    override val layoutResource: Int = R.layout.fragment_tour_guide_item_list_detail
    private var mServiceOptionList: ArrayList<TourItemServiceModel> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()

        initRecyclerView()
    }

    fun initList() {
        val mListSubList = ArrayList<TourItemServiceModel>()
        var mItem = TourItemServiceModel(image = Utils.getUrlFromResource(R.drawable.transportation_tuktuk, mActivity!!.packageName), name = "Tuk tuk", description = "Traditional motorbike trailer 1-4 seats", price = 120.50)
        mItem.id = "tuttuk"
        mListSubList.add(mItem)

        mItem = TourItemServiceModel(image = Utils.getUrlFromResource(R.drawable.transporatation_passapp, mActivity!!.packageName), name = "Pass App", description = "3-Wheel-Auto Riskhaw 1-3 seats", price = 35.50)
        mItem.id = "passapp"
        mListSubList.add(mItem)

        mItem = TourItemServiceModel(image = Utils.getUrlFromResource(R.drawable.transportation_shared, mActivity!!.packageName), name = "Shared", description = "Shared rides with others going your way", price = 50.00)
        mItem.id = "shared"
        mListSubList.add(mItem)

        mItem = TourItemServiceModel(image = Utils.getUrlFromResource(R.drawable.transportation_deluxe, mActivity!!.packageName), name = "Deluxe", description = "Newer cars with extra legroom", price = 95.30)
        mItem.id = "deluxe"
        mListSubList.add(mItem)

        mItem = TourItemServiceModel(image = Utils.getUrlFromResource(R.drawable.transportation_supreme, mActivity!!.packageName), name = "Supreme", description = "Luxury rides for 6 with professional drivers", price = 140.00)
        mItem.id = "supreme"
        mListSubList.add(mItem)

        mServiceOptionList = mListSubList

    }

    private fun initRecyclerView() {
        val mAdapter = TourGuideItemServiceAdapter(mServiceOptionList) {
            // Update Price
            // mViewModel.updateTotalPrice(mViewModel.getPriceBeforeExtra().get() ?: 0.0, mServiceOptionList) // Calculate Price
        }

        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(mActivity)
            adapter = mAdapter
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TourGuideItemListDetailFragment.

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TourGuideItemServiceFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            } */
    }
}