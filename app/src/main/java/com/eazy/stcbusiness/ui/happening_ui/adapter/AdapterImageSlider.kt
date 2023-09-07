package com.eazy.stcbusiness.ui.happening_ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.ItemImageSliderModel
import com.eazy.stcbusiness.utils.initImage
import com.eazy.stcbusiness.utils.setHeightOfScreen

class AdapterImageSlider (private val listImage: ArrayList<ItemImageSliderModel>, private val viewPager2: ViewPager2): RecyclerView.Adapter<AdapterImageSlider.SliderViewHolder>() {

    private var originalDotCount: Int = 0
    private var dotCount: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_image_slide_model, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(listImage[position])
        if (position == listImage.size - 2) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return listImage.size
    }


    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewPager: ImageView = itemView.findViewById(R.id.imageViewPager)
        var cardViewLayout : CardView = itemView.findViewById(R.id.cardViewLayout)
        fun setImage(sliderItems: ItemImageSliderModel) {
            // imageViewPager.layoutParams.height =  imageViewPager.context.setHeightOfScreen(4.0)
            imageViewPager.initImage(sliderItems.image)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private val runnable = Runnable {
        listImage.addAll(listImage)
        notifyDataSetChanged()
    }


    fun setDotCount(count: Int) {
        originalDotCount = count
        dotCount = if (count > 1) count else 0
        notifyDataSetChanged()
    }

//    override fun onViewAttachedToWindow(holder: SliderViewHolder) {
//        super.onViewAttachedToWindow(holder)
//        val position = holder.adapterPosition
//        if (position == 0 || position == itemCount - 1) {
//            // Update dot count dynamically based on the current position
//            if (originalDotCount == 1)
//                dotCount = 0
//            else
//                dotCount = originalDotCount + 1
//            notifyDataSetChanged()
//        }
//    }

}
