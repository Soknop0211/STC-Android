package com.eazy.stcbusiness.ui.home

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        private const val ACTION_TYPE = "ACTION_TYPE"
        const val HAPPENING_NOW_HOME = "HAPPENING_NOW_HOME"

        @JvmStatic
        fun newInstance(mAction : String) =
            HomeContentFragment().apply {
                arguments = Bundle().apply {
                    putString(ACTION_TYPE, mAction)
                }
            }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val item = inflater.inflate(R.layout.fragment_home_content, container, false)

        var mActionType = ""

        if (arguments != null && arguments!!.containsKey(ACTION_TYPE)) {
            mActionType = arguments!!.getString(ACTION_TYPE).toString()
        }

        item.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(mActivity)
            adapter = mActivity?.let { MainHomeShowItemAdapter(initList(mActionType) , it) {
                ThingToDoDetailActivity.gotoSearchDestinationThingToDoActivity(mActivity!!)
            } }
        }

        return item
    }

    private fun initList(mActionType : String) : ArrayList<CustomCategoryDataList> {
        val mList = ArrayList<CustomCategoryDataList>()

        /**** Happening Now ***/
        if (!TextUtils.isEmpty(mActionType)) {
            val mListSubList = ArrayList<CustomCategoryModel>()
            mListSubList.add(CustomCategoryModel(HAPPENING_NOW_HOME, "Phnom Penh", "10 Activities", "https://dev.booknow.asia/images/home_slider_1.jpg"))
            mListSubList.add(CustomCategoryModel(HAPPENING_NOW_HOME, "Siem Reap", "11 Activities", "https://dev.booknow.asia/images/home_slider_2.jpg"))
            mListSubList.add(CustomCategoryModel(HAPPENING_NOW_HOME, "Kep, Province","11 Activities", "https://dev.booknow.asia/images/home_slider_3.jpg"))
            mListSubList.add(CustomCategoryModel(HAPPENING_NOW_HOME, "Takeo", "10 Activities", "https://dev.booknow.asia/images/home_slider_4.jpg"))
            mListSubList.add(CustomCategoryModel(HAPPENING_NOW_HOME, "Kompong Spie", "11 Activities", "https://dev.booknow.asia/images/home_slider_5.jpg"))
            mListSubList.add(CustomCategoryModel(HAPPENING_NOW_HOME, "Kompot","11 Activities", "https://dev.booknow.asia/images/home_slider_6.jpg"))
            mListSubList.add(CustomCategoryModel(HAPPENING_NOW_HOME, "Kompot","11 Activities", "https://dev.booknow.asia/images/home_slider_6.jpg"))
            mList.add(CustomCategoryDataList(HAPPENING_NOW_HOME, mActivity!!.getString(R.string.up_comming_event), mListSubList, true))
        }

        /**** Things To Do ***/
        else {
            var mListSubList = ArrayList<CustomCategoryModel>()
            mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
            mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
            mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
            mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
            mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
            mListSubList.add(CustomCategoryModel(DISCOUNT, "Hot Promotion"))
            mList.add(CustomCategoryDataList(DISCOUNT,  mActivity!!.getString(R.string.special_offer), mListSubList, true))

            mListSubList = ArrayList()
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Phnom Penh", "10 Activities", "https://dev.booknow.asia/images/home_slider_1.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Siem Reap", "11 Activities", "https://dev.booknow.asia/images/home_slider_2.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Phnom Penh", "10 Activities", "https://dev.booknow.asia/images/home_slider_1.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Siem Reap", "11 Activities", "https://dev.booknow.asia/images/home_slider_2.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Phnom Penh", "10 Activities", "https://dev.booknow.asia/images/home_slider_1.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Siem Reap", "11 Activities", "https://dev.booknow.asia/images/home_slider_2.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Phnom Penh", "10 Activities", "https://dev.booknow.asia/images/home_slider_1.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Siem Reap", "11 Activities", "https://dev.booknow.asia/images/home_slider_2.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Phnom Penh", "10 Activities", "https://dev.booknow.asia/images/home_slider_1.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Siem Reap", "11 Activities", "https://dev.booknow.asia/images/home_slider_2.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Phnom Penh", "10 Activities", "https://dev.booknow.asia/images/home_slider_1.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Siem Reap", "11 Activities", "https://dev.booknow.asia/images/home_slider_2.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Phnom Penh", "10 Activities", "https://dev.booknow.asia/images/home_slider_1.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Siem Reap", "11 Activities", "https://dev.booknow.asia/images/home_slider_2.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Phnom Penh", "10 Activities", "https://dev.booknow.asia/images/home_slider_1.jpg"))
            mListSubList.add(CustomCategoryModel(HIGHLY_RECOMMEND, "Siem Reap", "11 Activities", "https://dev.booknow.asia/images/home_slider_2.jpg"))
            mList.add(CustomCategoryDataList(HIGHLY_RECOMMEND, mActivity!!.getString(R.string.highly_recommentd), mListSubList, true))
        }


        return mList
    }



}