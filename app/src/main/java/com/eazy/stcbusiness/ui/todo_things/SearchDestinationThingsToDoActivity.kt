package com.eazy.stcbusiness.ui.todo_things

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.base_dapter.AbsoluteFitLayoutManager
import com.eazy.stcbusiness.databinding.ActivitySearchDestinationThingsToDoBinding
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.ui.todo_things.adapter.HighlyRecommendAdapter
import com.eazy.stcbusiness.ui.todo_things.adapter.TodoDestinationAdapter

class SearchDestinationThingsToDoActivity : AppCompatActivity() {

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
            adapter = HighlyRecommendAdapter(SEARCH_BY_DESTINATION, initList())
            isNestedScrollingEnabled = true
        }

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