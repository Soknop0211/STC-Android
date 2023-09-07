package com.eazy.stcbusiness.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.SampleBaseFragment
import com.eazy.stcbusiness.base_dapter.MainHomeShowItemAdapter
import com.eazy.stcbusiness.model.CustomCategoryDataList
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.ui.todo_things.ThingToDoDetailActivity

class HomeFragment : SampleBaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
          //  layoutManager = LinearLayoutManager(mActivity)
            adapter = mActivity?.let { MainHomeShowItemAdapter(initList() , it) {
                ThingToDoDetailActivity.gotoSearchDestinationThingToDoActivity(mActivity!!)
            } }
        }


        return view
    }

    private fun initList() : ArrayList<CustomCategoryDataList> {
        val mList = ArrayList<CustomCategoryDataList>()

        var mListSubList = ArrayList<CustomCategoryModel>()
        var drawable = ResourcesCompat.getDrawable(resources, R.drawable.angkor_wat_temple, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.ABOUT_SIEM_REAP, "About Siem Reap", drawable!!))
        mList.add(CustomCategoryDataList(mainAction = HomeContentFragment.ABOUT_SIEM_REAP,"About Siem Reap", data = mListSubList))

        mListSubList = ArrayList()
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.happening_now, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.CATEGORY, "Happening Now", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.thing_to_do, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.CATEGORY, "Things To Do", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.custom_forum, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.CATEGORY, "Custom Forum", drawable!!))
        mList.add(CustomCategoryDataList(HomeContentFragment.CATEGORY, "Custom Forum", mListSubList))

        mListSubList = ArrayList()
        mListSubList.add(CustomCategoryModel(HomeContentFragment.DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(HomeContentFragment.DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(HomeContentFragment.DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(HomeContentFragment.DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(HomeContentFragment.DISCOUNT, "Hot Promotion"))
        mListSubList.add(CustomCategoryModel(HomeContentFragment.DISCOUNT, "Hot Promotion"))
        mList.add(CustomCategoryDataList(HomeContentFragment.DISCOUNT, "Hot Promotion", mListSubList))

        mListSubList = ArrayList()
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.tours_guide, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.MAIN_CATEGORY, "Tours Guide", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.transportation, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.MAIN_CATEGORY, "Transportations", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.restaurants, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.MAIN_CATEGORY, "Restaurants", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.hotel, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.MAIN_CATEGORY, "Hotels", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.ticket, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.MAIN_CATEGORY, "Tickets", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.spa, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.MAIN_CATEGORY, "Spa", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.e_visa, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.MAIN_CATEGORY, "E-Visa", drawable!!))
        drawable = ResourcesCompat.getDrawable(resources, R.drawable.tokenize_card, null)
        mListSubList.add(CustomCategoryModel(HomeContentFragment.MAIN_CATEGORY, "Tokenize card", drawable!!))

        mList.add(CustomCategoryDataList(HomeContentFragment.MAIN_CATEGORY, "Tokenize card", mListSubList))

        return mList
    }


    @SuppressLint("DetachAndAttachSameFragment")
    private fun reLoadFragment(viewpager : ViewPager) {
//        val hotelHomeListHotelFragment = HomeContentFragment()
////        val pagerAdapter = HotelHomeListViewPager(childFragmentManager)
////        pagerAdapter.addFragment(hotelHomeListHotelFragment, "fragment")
////        viewpager.adapter = pagerAdapter
//        val support = childFragmentManager
//        val tran = support.beginTransaction()

    }

//    private fun replaceFragment() {
//        val hotelHomeListHotelFragment = HomeContentFragment()
//        val transaction = childFragmentManager.beginTransaction()
//        transaction.replace(R.id.viewpager, hotelHomeListHotelFragment, hotelHomeListHotelFragment.tag)
//        transaction.disallowAddToBackStack()
//        transaction.commit()
//    }

}