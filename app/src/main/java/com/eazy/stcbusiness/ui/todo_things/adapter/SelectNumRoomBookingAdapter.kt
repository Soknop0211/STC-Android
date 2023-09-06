package com.eazy.stcbusiness.ui.todo_things.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.NumRoomBookingModel
import com.eazy.stcbusiness.utils.initText

class SelectNumRoomBookingAdapter(private val context : Context, private val list: List<NumRoomBookingModel>, private val onClickListener : OnClickCallBackLister) : RecyclerView.Adapter<SelectNumRoomBookingAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.select_number_of_room_booking, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item : NumRoomBookingModel = list[position]
        holder.numVal.initText(item.numValues)

        when (item.action) {
            "room" -> {
                holder.selectLabelTv.initText(R.string.adults, context)
                holder.desLabelTv.initText(R.string.age_adults, context)
            }
            "adults" -> {
                holder.selectLabelTv.initText(R.string.youths, context)
                holder.desLabelTv.initText(R.string.age_youths, context)
            }
            else -> {
                holder.selectLabelTv.initText(R.string.children, context)
                holder.desLabelTv.initText(R.string.age_children, context)
            }
        }
        holder.line.visibility = if ((list.size - 1) == position) View.GONE else View.VISIBLE

        holder.add.setOnClickListener { onClickListener.onClickCallBack(item, "add") }

        holder.minus.setOnClickListener { onClickListener.onClickCallBack(item, "minus") }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val selectLabelTv : TextView = itemView.findViewById(R.id.selectLabelTv)
        val desLabelTv : TextView = itemView.findViewById(R.id.desLabelTv)
        val minus : ImageView = itemView.findViewById(R.id.minus)
        val add : ImageView = itemView.findViewById(R.id.add)
        val numVal : TextView = itemView.findViewById(R.id.numVal)
        val line : View = itemView.findViewById(R.id.line)
    }

    interface OnClickCallBackLister{
        fun onClickCallBack(value : NumRoomBookingModel, actionBtn : String)
    }
}