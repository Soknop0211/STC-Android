package com.eazy.stcbusiness.base_dapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.CustomCategoryModel

class CustomItemHomeAdapter(private val list: List<CustomCategoryModel>) : RecyclerView.Adapter<CustomItemHomeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtBgCorner: TextView = itemView.findViewById(R.id.txtBgCorner)
        val image: ImageView = itemView.findViewById(R.id.image)
        val txtTittle: TextView = itemView.findViewById(R.id.txtTittle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_sub_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtBgCorner.visibility =  if (position == 0) View.VISIBLE else View.GONE
        holder.txtTittle.visibility =  if (position > 0) View.VISIBLE else View.GONE
        val lastIndex = list.size - 1
    }

    override fun getItemCount(): Int {
        return list.size
    }

}