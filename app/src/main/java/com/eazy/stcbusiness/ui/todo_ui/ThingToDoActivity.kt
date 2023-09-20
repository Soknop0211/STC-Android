package com.eazy.stcbusiness.ui.todo_ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.SampleBaseActivity
import com.eazy.stcbusiness.base_dapter.AbsoluteFitLayoutManager
import com.eazy.stcbusiness.databinding.ActivityThingToDoBinding
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.ui.todo_ui.adapter.TodoDestinationAdapter

class ThingToDoActivity : SampleBaseActivity()  {


    companion object {
        fun gotoThingToDoActivity(activity: Context){
            val intent = Intent(activity, ThingToDoActivity::class.java)
            activity.startActivity(intent)
        }
    }


    private lateinit var binding : ActivityThingToDoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThingToDoBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.recyclerView.apply {
            layoutManager =  AbsoluteFitLayoutManager(
                context,
                1,
                RecyclerView.HORIZONTAL,
                false,
                3
            )
            adapter = TodoDestinationAdapter(this@ThingToDoActivity, initList()) {
                SearchDestinationThingsToDoActivity.gotoSearchDestinationThingToDoActivity(this@ThingToDoActivity)
            }
            isNestedScrollingEnabled = true
        }

        binding.iconBack.setOnClickListener { finish() }

        replaceFragment()
    }

    private fun replaceFragment() {
        val hotelHomeListHotelFragment = HomeContentFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.viewpager, hotelHomeListHotelFragment, hotelHomeListHotelFragment.tag)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    private fun initList() : ArrayList<CustomCategoryModel> {
        val mList = ArrayList<CustomCategoryModel>()

        mList.add(CustomCategoryModel("1", "Phnom Penh", "10 Activities", "https://dev.booknow.asia/images/home_slider_1.jpg"))
        mList.add(CustomCategoryModel("2", "Siem Reap", "11 Activities", "https://dev.booknow.asia/images/home_slider_2.jpg"))
        mList.add(CustomCategoryModel("3", "Kep, Province","11 Activities", "https://dev.booknow.asia/images/home_slider_3.jpg"))
        mList.add(CustomCategoryModel("4", "Takeo", "10 Activities", "https://dev.booknow.asia/images/home_slider_4.jpg"))
        mList.add(CustomCategoryModel("5", "Kompong Spie", "11 Activities", "https://dev.booknow.asia/images/home_slider_5.jpg"))
        mList.add(CustomCategoryModel("6", "Kompot","11 Activities", "https://dev.booknow.asia/images/home_slider_6.jpg"))
        return mList
    }
}