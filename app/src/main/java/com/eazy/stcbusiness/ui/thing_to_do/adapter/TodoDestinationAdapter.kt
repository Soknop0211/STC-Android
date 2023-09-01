package com.eazy.stcbusiness.ui.thing_to_do.adapter

import android.app.Activity
import android.opengl.ETC1.getWidth
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


class TodoDestinationAdapter(private val mActivity : Activity, private val mList: List<CustomCategoryModel>) : RecyclerView.Adapter<TodoDestinationAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTittle: TextView = itemView.findViewById(R.id.txtTittle)
        val image: ImageView = itemView.findViewById(R.id.image)
        val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.destination_layout, parent, false)
        val itemViewParam = LinearLayout.LayoutParams(mActivity.getWidth() / 3, LinearLayout.LayoutParams.WRAP_CONTENT)
        rootView.layoutParams = itemViewParam
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mItem = mList[position]

        holder.txtTittle.text =  mItem.name
        holder.txtDescription.text =  mItem.description
        holder.image.initImage(mItem.urlImage)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}