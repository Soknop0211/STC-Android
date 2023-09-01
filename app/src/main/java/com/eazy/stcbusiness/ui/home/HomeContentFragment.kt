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

class HomeContentFragment : SampleBaseFragment() {

    companion object {
        const val ABOUT_SIEM_REAP = "VISIT_SIEM_REAP"
        const val CATEGORY = "CATEGORY"
        const val DISCOUNT = "DISCOUNT"
        const val MAIN_CATEGORY = "MAIN_CATEGORY"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val item = inflater.inflate(R.layout.fragment_home_content, container, false)

        item.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(mActivity)
            adapter = mActivity?.let { MainHomeShowItemAdapter(initList() , it) }
        }

        return item
    }

    private fun initList() : ArrayList<CustomCategoryDataList> {
        val mList = ArrayList<CustomCategoryDataList>()

        var mListSubList = ArrayList<CustomCategoryModel>()
        var drawable = ResourcesCompat.getDrawable(resources, R.drawable.angkor_wat_temple, null)
        mListSubList.add(CustomCategoryModel(ABOUT_SIEM_REAP, "About Siem Reap", drawable!!))
        mList.add(CustomCategoryDataList(ABOUT_SIEM_REAP, mListSubList))

        mListSubList = ArrayList()
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.happening_now, null)
        mListSubList.add(CustomCategoryModel(CATEGORY, "Happening Now", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.thing_to_do, null)
        mListSubList.add(CustomCategoryModel(CATEGORY, "Things To Do", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.custom_forum, null)
        mListSubList.add(CustomCategoryModel(CATEGORY, "Custom Forum", drawable!!))
        mList.add(CustomCategoryDataList(CATEGORY, mListSubList))

        mListSubList = ArrayList()
        mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
        mList.add(CustomCategoryDataList(DISCOUNT, mListSubList))

        mListSubList = ArrayList()
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.transportation, null)
        mListSubList.add(CustomCategoryModel(MAIN_CATEGORY, "Transportation", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.airport, null)
        mListSubList.add(CustomCategoryModel(MAIN_CATEGORY, "Airport", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.e_visa, null)
        mListSubList.add(CustomCategoryModel(MAIN_CATEGORY, "E-Visa", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.transportation, null)
        mListSubList.add(CustomCategoryModel(MAIN_CATEGORY, "Transportation", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.airport, null)
        mListSubList.add(CustomCategoryModel(MAIN_CATEGORY, "Airport", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.e_visa, null)
        mListSubList.add(CustomCategoryModel(MAIN_CATEGORY, "E-Visa", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.e_visa, null)
        mListSubList.add(CustomCategoryModel(MAIN_CATEGORY, "E-Visa", drawable!!))

        mList.add(CustomCategoryDataList(MAIN_CATEGORY, mListSubList))

        return mList
    }


}