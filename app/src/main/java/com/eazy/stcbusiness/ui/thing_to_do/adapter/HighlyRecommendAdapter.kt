package com.eazy.stcbusiness.ui.thing_to_do.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.utils.getWidth
import com.eazy.stcbusiness.utils.initImage
import com.eazy.stcbusiness.utils.setTextStrikeStyle

class HighlyRecommendAdapter(private val mList: List<CustomCategoryModel>) : RecyclerView.Adapter<HighlyRecommendAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val originalPrice: TextView = itemView.findViewById(R.id.originalPrice)
        val image: ImageView = itemView.findViewById(R.id.image)
//        val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.highly_recommend_layout, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mItem = mList[position]

        // Set original price
        holder.originalPrice.setTextStrikeStyle()

//        holder.txtTittle.text =  mItem.name
//        holder.txtDescription.text =  mItem.description
        holder.image.initImage(mItem.urlImage)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}