package com.eazy.stcbusiness.ui.tours_guide

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.SampleBaseFragment_
import com.eazy.stcbusiness.base_dapter.CustomImageGridLayoutAdapter
import com.eazy.stcbusiness.databinding.FragmentTourGuideItemDetailBinding
import com.eazy.stcbusiness.utils.Utils


class TourGuideItemDetailFragment : SampleBaseFragment_<FragmentTourGuideItemDetailBinding>() {

    override val layoutResource: Int = R.layout.fragment_tour_guide_item_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = arrayListOf(
            "https://dev.booknow.asia/images/home_slider_1.jpg",
            "https://dev.booknow.asia/images/home_slider_3.jpg",
            "https://dev.booknow.asia/images/home_slider_4.jpg",
            "https://dev.booknow.asia/images/home_slider_5.jpg",
            "https://dev.booknow.asia/images/home_slider_6.jpg"
        )

        initRecyclerImage(imageList)


    }

    private fun initRecyclerImage(slideModels: ArrayList <String>){
        mBinding.recyclerViewActivity.apply {
            layoutManager = Utils.spanGridImageLayoutManager(slideModels, mActivity!!)
            adapter = CustomImageGridLayoutAdapter("show_5_items", mActivity!!, slideModels, object : CustomImageGridLayoutAdapter.OnClickCallBackLister{
                override fun onClickCallBack(value: String) {
                    /** val intent = Intent(this@TourGuideItemDetailFragment, ShowAllImageActivity::class.java)
                    intent.putExtra("image_list", slideModels)
                    intent.putExtra("hotel_name", hotelNameTv.text.toString())
                    startActivity(intent) ***/
                }

            })
            isNestedScrollingEnabled = false
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TourGuideItemDetailFragment.

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TourGuideItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
         */
    }
}