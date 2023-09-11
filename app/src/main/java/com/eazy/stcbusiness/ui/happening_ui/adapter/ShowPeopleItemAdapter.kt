package com.eazy.stcbusiness.ui.happening_ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.ui.todo_ui.adapter.BindingViewHolder
import com.eazy.stcbusiness.view_model.ShowImageViewModel

class ShowPeopleItemAdapter(private val list : List<String>, private val mIsSponsor : Boolean) : RecyclerView.Adapter<BindingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder.Builder(parent, R.layout.custom_image_circle_layout).build();
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val mItem = list[position]
        val viewModel = ShowImageViewModel(mItem, (list.size > 4 && list.size - 1 == position), mIsSponsor)
        holder.setVariable(BR.viewModel, viewModel)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}