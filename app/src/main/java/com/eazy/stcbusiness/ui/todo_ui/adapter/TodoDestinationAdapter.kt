package com.eazy.stcbusiness.ui.todo_ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.ui.todo_ui.SearchDestinationThingsToDoActivity.Companion.gotoSearchDestinationThingToDoActivity
import com.eazy.stcbusiness.utils.getWidth
import com.eazy.stcbusiness.utils.initImage


class TodoDestinationAdapter(private val mActivity : Activity,
                             private val mList: List<CustomCategoryModel>, private val mListener : (CustomCategoryModel) -> Unit) : RecyclerView.Adapter<TodoDestinationAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTittle: TextView = itemView.findViewById(R.id.txtTittle)
        val image: ImageView = itemView.findViewById(R.id.image)
        val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        val mainLayout: CardView = itemView.findViewById(R.id.mainLayout)
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

        holder.mainLayout.setOnClickListener {
            mListener.invoke(mItem)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}