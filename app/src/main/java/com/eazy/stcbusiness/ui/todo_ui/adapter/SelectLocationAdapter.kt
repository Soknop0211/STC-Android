package com.eazy.stcbusiness.ui.todo_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.databinding.DestinationLocationLayoutBinding
import com.eazy.stcbusiness.model.LocationModel
import com.eazy.stcbusiness.view_model.DestinationItemLocationViewModel

class SelectLocationAdapter(private val mSelectedId: String, private val list : List<LocationModel>, private val onClickListener : OnClickCallBackLister) : RecyclerView.Adapter<SelectLocationAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DestinationLocationLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBinding(mSelectedId, list[position])

        holder.itemView.setOnClickListener {
            onClickListener.onClickCallBack(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: DestinationLocationLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBinding(mSelectedId: String, item : LocationModel) {

//            holder.itemNameTv.text = item.name
//
//            holder.checkClick.visibility = if(mSelectedId == item.id) View.VISIBLE else View.INVISIBLE
//            holder.line.visibility = if((list.size - 1) == position) View.GONE else View.VISIBLE
//
//            holder.itemView.setOnClickListener { onClickListener.onClickCallBack(item) }
//
//            holder.mainLayout.setBackgroundColor(Color.WHITE)

            val mViewModel = DestinationItemLocationViewModel(mSelectedId, item)
            binding.setVariable(BR.viewModel, mViewModel)
        }
    }

    interface OnClickCallBackLister{
        fun onClickCallBack(value : LocationModel)
    }
}