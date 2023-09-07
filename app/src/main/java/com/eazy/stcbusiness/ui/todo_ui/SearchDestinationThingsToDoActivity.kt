package com.eazy.stcbusiness.ui.todo_ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.base.SampleBaseActivity
import com.eazy.stcbusiness.databinding.ActivitySearchDestinationThingsToDoBinding
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.ui.todo_ui.adapter.HighlyRecommendAdapter
import com.eazy.stcbusiness.ui.todo_ui.fragment.DestinationLocationBottomSheetFragment
import com.eazy.stcbusiness.utils.listener.CustomResponseOnClickListener
import com.eazy.stcbusiness.utils.listener.CustomSetOnClickViewListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDestinationThingsToDoActivity : SampleBaseActivity() {

    companion object {
        fun gotoSearchDestinationThingToDoActivity(activity: Context){
            val intent = Intent(activity, SearchDestinationThingsToDoActivity::class.java)
            activity.startActivity(intent)
        }
        const val SEARCH_BY_DESTINATION = "SEARCH_BY_DESTINATION"
    }

    private lateinit var binding : ActivitySearchDestinationThingsToDoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchDestinationThingsToDoBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.iconBack.setOnClickListener { finish() }

        binding.recyclerView.apply {
            layoutManager =  LinearLayoutManager(this@SearchDestinationThingsToDoActivity)
            adapter = HighlyRecommendAdapter(SEARCH_BY_DESTINATION, initList()) {
                ThingToDoDetailActivity.gotoSearchDestinationThingToDoActivity(this@SearchDestinationThingsToDoActivity)
            }
            isNestedScrollingEnabled = true
        }

        binding.searchLayout.setOnClickListener(CustomSetOnClickViewListener (object :
            CustomResponseOnClickListener {
            override fun onClick(view: View) {
                val mDestinationBottomSheet = DestinationLocationBottomSheetFragment.newInstance("")
                mDestinationBottomSheet.show(supportFragmentManager, DestinationLocationBottomSheetFragment::class.java.name)
            }

        }))

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