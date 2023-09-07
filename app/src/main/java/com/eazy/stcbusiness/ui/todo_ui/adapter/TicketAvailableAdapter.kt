package com.eazy.stcbusiness.ui.todo_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.databinding.TicketAvailableLayoutBinding
import com.eazy.stcbusiness.model.TicketAvailableModel
import com.eazy.stcbusiness.view_model.TicketItemAvailableViewModel

class TicketAvailableAdapter(private val list : List<TicketAvailableModel>, private val mOnClickListener : (TicketAvailableModel?) -> Unit) :
    RecyclerView.Adapter<TicketAvailableAdapter.ViewHolder>(){

    fun updateProductOrderCounter(ids: String) : Double {
        var totalPrice = 0.0

        for (i in list.indices) {

            val indexI: Int = if (list[i].isClick) i else 0
            val indexJ: Int = if (list[i].id == ids) i else 0

            if (list[i].id == ids) {
                list[i].isClick = true
                totalPrice = list[i].price
            } else {
                list[i].isClick = false
            }

            notifyItemChanged(indexI)

            notifyItemChanged(indexJ)
        }

        return totalPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TicketAvailableLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBinding(list[position], mOnClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: TicketAvailableLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBinding(item : TicketAvailableModel, mOnClickListener : (TicketAvailableModel?) -> Unit) {
            val mViewModel = TicketItemAvailableViewModel(binding.eventTitleTv.context, item, mOnClickListener)
            binding.setVariable(BR.viewModel, mViewModel)
        }
    }
}