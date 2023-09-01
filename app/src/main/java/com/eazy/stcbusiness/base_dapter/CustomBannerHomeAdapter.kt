package com.eazy.stcbusiness.base_dapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.ui.thing_to_do.ThingToDoActivity.Companion.gotoThingToDoActivity
import com.eazy.stcbusiness.utils.initImage

class CustomBannerHomeAdapter(private val mContext : Context, private val mType: String, private val list: List<CustomCategoryModel>) : RecyclerView.Adapter<CustomBannerHomeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val imageItem: ImageView = itemView.findViewById(R.id.image)
        val imageItem2: ImageView = itemView.findViewById(R.id.image2)
        val mainLayout: CardView = itemView.findViewById(R.id.mainLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_content_info_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mItem = list[position]
        holder.textTitle.text = mItem.name

        holder.textTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, if (mType == HomeContentFragment.ABOUT_SIEM_REAP) 18F else 11F)

        holder.imageItem.setImageDrawable(mItem.icon)
        holder.imageItem2.setImageDrawable(mItem.icon)

        holder.imageItem2.visibility = if (mType == HomeContentFragment.ABOUT_SIEM_REAP) View.VISIBLE else View.GONE
        holder.imageItem.visibility =  if (mType == HomeContentFragment.ABOUT_SIEM_REAP) View.GONE else View.VISIBLE

        if (mType == HomeContentFragment.CATEGORY && mItem.name.equals("Things To Do")) {
            holder.mainLayout.setOnClickListener {
                gotoThingToDoActivity(mContext)
            }
        }


//        holder.txtHeaderTitle.text = if (!TextUtils.isEmpty(list[position].description)) (String.format("%s : ", list[position].description)) else ""
//        holder.imageItem.initImage(if (list[position].icon != null) list[position].icon else "")
//
//        holder.txtHeaderTitle.visibility = if (!TextUtils.isEmpty(list[position].description)) View.VISIBLE else View.GONE
//        holder.imageItem.visibility = if (!TextUtils.isEmpty(list[position].icon)) View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int {
        return if (mType == HomeContentFragment.ABOUT_SIEM_REAP)  1 else 3
    }

}