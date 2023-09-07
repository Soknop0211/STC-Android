package com.eazy.stcbusiness.utils

import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.eazy.stcbusiness.model.ItemImageSliderModel
import com.eazy.stcbusiness.ui.happening_ui.adapter.AdapterImageSlider

class CustomSliderImageClass {
    companion object {
        fun initImageSlideViewPager(startEndPadding : Int, viewPager2 : ViewPager2, sliderHandler: Handler, sliderRunnable : Runnable, imageModelList: MutableList<ItemImageSliderModel>){
            val imageList: ArrayList<ItemImageSliderModel> = ArrayList()
            imageList.addAll(imageModelList)

            viewPager2.setPadding(startEndPadding, 0, startEndPadding, 0)
            viewPager2.adapter = AdapterImageSlider(imageList, viewPager2)
            viewPager2.clipToPadding = false
            viewPager2.clipChildren = false
            viewPager2.offscreenPageLimit = 3
            viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(40))

            compositePageTransformer.addTransformer { page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }

            viewPager2.setPageTransformer(compositePageTransformer)

            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(sliderRunnable)
                    sliderHandler.postDelayed(sliderRunnable, 3000)// slide duration 3 seconds
                }
            })
        }
    }
}