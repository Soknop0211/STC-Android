package com.eazy.stcbusiness.base_dapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.utils.initImage
import com.eazy.stcbusiness.utils.listener.CustomResponseOnClickListener
import com.eazy.stcbusiness.utils.listener.CustomSetOnClickViewListener

class CustomCategoryHomeAdapter(private val mList: List<CustomCategoryModel>, private val mListener : (CustomCategoryModel) -> Unit) : RecyclerView.Adapter<CustomCategoryHomeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.nameTv)
        val imageItem: ImageView = itemView.findViewById(R.id.image)
        val itemLayout: CardView = itemView.findViewById(R.id.itemLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home_category_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mItem = mList[position]
        holder.textTitle.text =  mItem.name
        holder.imageItem.initImage(mItem.icon)
        holder.itemLayout.setOnClickListener(CustomSetOnClickViewListener(object : CustomResponseOnClickListener {
            override fun onClick(view: View) {
                mListener.invoke(mItem)
            }

        }))

    }

    override fun getItemCount(): Int {
        return mList.size
    }

}