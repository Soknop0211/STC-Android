package com.eazy.stcbusiness.ui.happening_ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.databinding.EventUpcomingLayoutBinding
import com.eazy.stcbusiness.databinding.ListConciergeFilterProductCriteriaItemBinding
import com.eazy.stcbusiness.model.ItemRecentSearchModel
import com.eazy.stcbusiness.ui.todo_ui.adapter.BindingViewHolder
import com.eazy.stcbusiness.utils.listener.CustomResponseOnClickListener
import com.eazy.stcbusiness.utils.listener.CustomSetOnClickViewListener
import com.eazy.stcbusiness.view_model.ItemRecentSearchViewModel

class HappeningItemRecentSearchAdapter(private val list : List<ItemRecentSearchModel>, val mListener : (ItemRecentSearchModel) -> Unit) : RecyclerView.Adapter<BindingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ListConciergeFilterProductCriteriaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder.Builder(parent, R.layout.item_recent_search_layout).build();
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val mItem = list[position]
        val viewModel = ItemRecentSearchViewModel(mItem) {
            mListener.invoke(it)
        }
        holder.setVariable(BR.viewModel, viewModel)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: EventUpcomingLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}