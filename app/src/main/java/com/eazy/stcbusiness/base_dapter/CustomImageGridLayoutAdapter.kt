package com.eazy.stcbusiness.base_dapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.utils.dpToPx
import com.eazy.stcbusiness.utils.listener.CustomResponseOnClickListener
import com.eazy.stcbusiness.utils.listener.CustomSetOnClickViewListener
import com.eazy.stcbusiness.utils.setHeightOfScreen

class CustomImageGridLayoutAdapter(private val action : String, private val context : Context, private val list: List<String>, private val onClickListener : OnClickCallBackLister) : RecyclerView.Adapter<CustomImageGridLayoutAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.custom_image_view_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item : String = list[position]
        Glide.with(holder.image)
            .load(item)
            .placeholder(R.drawable.no_image)
            .into(holder.image)

        holder.totalImage.text = String.format("%s %s", "+", list.size.toString())

        holder.totalImage.visibility =
            if(action == "show_all_image") View.GONE else{ if (position == 4) View.VISIBLE else View.GONE}

        val heightImg: Int = if (action != "show_all_image"){
            if (list.size == 1 || list.size == 2){
                context.setHeightOfScreen(4.0)
            } else {
                context.dpToPx(130)
            }
        } else {
            context.dpToPx(130)
        }
        holder.imageLayout.layoutParams.height = heightImg

        holder.itemView.setOnClickListener (CustomSetOnClickViewListener (object : CustomResponseOnClickListener {
            override fun onClick(view: View) {
                onClickListener.onClickCallBack(item)
            }
        }))
    }

    override fun getItemCount(): Int {
        return if (action == "show_all_image"){
            list.size
        } else {
            if (list.size > 5){
                5
            } else {
                list.size
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.image)
        val totalImage : TextView = itemView.findViewById(R.id.totalImage)
        val imageLayout : CardView = itemView.findViewById(R.id.imageLayout)
    }

    interface OnClickCallBackLister{
        fun onClickCallBack(value : String)
    }
}