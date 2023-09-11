package com.eazy.stcbusiness.ui.happening_ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.ItemCategoryDateModel
import com.eazy.stcbusiness.ui.todo_ui.adapter.BindingViewHolder
import com.eazy.stcbusiness.view_model.ItemCategoryViewModel

class EventUpComingAdapter(private val list : List<ItemCategoryDateModel>) : RecyclerView.Adapter<BindingViewHolder>(){

    private val mViewModel: HashMap<String, ItemCategoryViewModel> = HashMap()
    private var mSelectedPaymentIndex = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
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

}