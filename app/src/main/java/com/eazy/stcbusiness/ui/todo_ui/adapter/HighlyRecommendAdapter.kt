package com.eazy.stcbusiness.ui.todo_ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.ui.todo_ui.SearchDestinationThingsToDoActivity
import com.eazy.stcbusiness.utils.initImage
import com.eazy.stcbusiness.utils.listener.CustomResponseOnClickListener
import com.eazy.stcbusiness.utils.listener.CustomSetOnClickViewListener
import com.eazy.stcbusiness.utils.setTextStrikeStyle

class HighlyRecommendAdapter(private val mType : String,
                             private val mList: List<CustomCategoryModel>,
                             private val mListener : (CustomCategoryModel) -> Unit
) : RecyclerView.Adapter<HighlyRecommendAdapter.ViewHolder>() {

    companion object {
        const val HIGHLY_RECOMMEND = 0X002
        const val SEARCH_BY_DESTINATION = 0X001
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val originalPrice: TextView = itemView.findViewById(R.id.originalPrice)
        val image: ImageView = itemView.findViewById(R.id.image)
//        val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == HIGHLY_RECOMMEND) {
            val rootView = LayoutInflater.from(parent.context).inflate(R.layout.highly_recommend_layout, parent, false)
            ViewHolder(rootView)
        } else {
            val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_search_by_destination, parent, false)
            ViewHolder(rootView)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mItem = mList[position]

        // Set original price
        holder.originalPrice.setTextStrikeStyle()

//        holder.txtTittle.text =  mItem.name
//        holder.txtDescription.text =  mItem.description
        holder.image.initImage(mItem.urlImage)

        holder.itemView.setOnClickListener (CustomSetOnClickViewListener(object : CustomResponseOnClickListener {
            override fun onClick(view: View) {
                mListener.invoke(mItem)
            }
        }))
    }

    override fun getItemViewType(position: Int): Int {
        if (mType == SearchDestinationThingsToDoActivity.SEARCH_BY_DESTINATION) {
            return SEARCH_BY_DESTINATION
        } else if (mType == HomeContentFragment.HIGHLY_RECOMMEND) {
            return HIGHLY_RECOMMEND
        }
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}