package com.eazy.stcbusiness.ui.transportation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.ui.todo_ui.adapter.BindingViewHolder
import com.eazy.stcbusiness.view_model.ItemImageViewModel

class TaxiLookingForAdapter(private val list : List<CustomCategoryModel>, private val mOnClick : (CustomCategoryModel) -> Unit) : RecyclerView.Adapter<BindingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder.Builder(parent, R.layout.taxi_looking_for_item_layout).build()
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val mItem = list[position]
        val viewModel = mItem.icon?.let { ItemImageViewModel(icon = it, mItem.name ?: "", if (position == 0) "Now" else "") {
            mOnClick.invoke(mItem)
        } }
        holder.setVariable(BR.viewModel, viewModel)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}