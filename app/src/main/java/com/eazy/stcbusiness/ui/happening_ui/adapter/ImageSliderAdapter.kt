package com.eazy.stcbusiness.ui.happening_ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.ItemImageSliderModel

class ImageSliderAdapter(
    private val mContext: Context,
    private val theSlideItemsModelClassList: List<ItemImageSliderModel>
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val sliderLayout = inflater.inflate(R.layout.custom_image_slide_model, null)
        val mImageView = sliderLayout.findViewById<ImageView>(R.id.imageViewPager)
        Glide.with(mContext)
            .load(theSlideItemsModelClassList[position].image)
            .into(mImageView)
        container.addView(sliderLayout)
        return sliderLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return theSlideItemsModelClassList.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }

}