package com.eazy.stcbusiness.ui.happening_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.databinding.EventUpcomingLayoutBinding
import com.eazy.stcbusiness.databinding.ListConciergeFilterProductCriteriaItemBinding
import com.eazy.stcbusiness.model.ItemCategoryDateModel
import com.eazy.stcbusiness.model.LocationModel
import com.eazy.stcbusiness.ui.todo_ui.adapter.BindingViewHolder
import com.eazy.stcbusiness.view_model.FilterByDestinationViewModel
import com.eazy.stcbusiness.view_model.ItemCategoryViewModel

class EventUpComingAdapter(private val list : List<ItemCategoryDateModel>) : RecyclerView.Adapter<BindingViewHolder>(){

    private val mViewModel: HashMap<String, ItemCategoryViewModel> = HashMap()
    private var mSelectedPaymentIndex = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ListConciergeFilterProductCriteriaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder.Builder(parent, R.layout.event_upcoming_layout).build();
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val mItem = list[position]
        val viewModel = ItemCategoryViewModel(mItem)
        holder.setVariable(BR.viewModel, viewModel)

        holder.binding.root.setOnClickListener {
            viewModel.setSelected(true)
            unselectOption(mItem.id ?: "")
        }

        mViewModel[mItem.id ?: ""] = viewModel
        if (position == mSelectedPaymentIndex) {
            holder.binding.root.performClick()
            mSelectedPaymentIndex = -1
        }
    }

    private fun unselectOption(excludeCheckingId: String) {
        for ((key, value) in mViewModel) {
            if (key != excludeCheckingId) {
                value.setSelected(false)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: EventUpcomingLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface OnClickCallBackLister{
        fun onClickCallBack(value : ItemCategoryDateModel)
    }

}