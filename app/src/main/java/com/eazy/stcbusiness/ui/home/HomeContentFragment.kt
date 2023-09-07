package com.eazy.stcbusiness.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.SampleBaseFragment
import com.eazy.stcbusiness.base_dapter.MainHomeShowItemAdapter
import com.eazy.stcbusiness.model.CustomCategoryDataList
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.ui.todo_ui.ThingToDoDetailActivity

class HomeContentFragment : SampleBaseFragment() {

    companion object {
        const val ABOUT_SIEM_REAP = "VISIT_SIEM_REAP"
        const val CATEGORY = "CATEGORY"
        const val DISCOUNT = "DISCOUNT"
        const val MAIN_CATEGORY = "MAIN_CATEGORY"
        const val HIGHLY_RECOMMEND = "HIGHLY_RECOMMEND"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val item = inflater.inflate(R.layout.fragment_home_content, container, false)

        item.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(mActivity)
            adapter = mActivity?.let { MainHomeShowItemAdapter(initList() , it) {
                ThingToDoDetailActivity.gotoSearchDestinationThingToDoActivity(mActivity!!)
            } }
        }

        return item
    }

    private fun initList() : ArrayList<CustomCategoryDataList> {
        val mList = ArrayList<CustomCategoryDataList>()

        var mListSubList = ArrayList<CustomCategoryModel>()
        mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
        mList.add(CustomCategoryDataList(DISCOUNT, "Special Offer", mListSubList, true))

        mListSubList = ArrayList()
        mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Phnom Penh", "10 Activities", "https://dev.booknow.asia/images/home_slider_1.jpg"))
        mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Siem Reap", "11 Activities", "https://dev.booknow.asia/images/home_slider_2.jpg"))
        mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Kep, Province","11 Activities", "https://dev.booknow.asia/images/home_slider_3.jpg"))
        mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Takeo", "10 Activities", "https://dev.booknow.asia/images/home_slider_4.jpg"))
        mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Kompong Spie", "11 Activities", "https://dev.booknow.asia/images/home_slider_5.jpg"))
        mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Kompot","11 Activities", "https://dev.booknow.asia/images/home_slider_6.jpg"))
        mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Kompot","11 Activities", "https://dev.booknow.asia/images/home_slider_6.jpg"))
        mList.add(CustomCategoryDataList(HIGHLY_RECOMMEND, "Highly Recommend", mListSubList, true))

        return mList
    }



}