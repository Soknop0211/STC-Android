package com.eazy.stcbusiness.ui.todo_things.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.databinding.ListConciergeFilterProductCriteriaItemBinding
import com.eazy.stcbusiness.model.LocationModel
import com.eazy.stcbusiness.view_model.FilterByDestinationViewModel

class StarRatingAdapter(private val list : List<LocationModel>, private val mType : String, private val onClickListener : OnClickCallBackLister) : RecyclerView.Adapter<BindingViewHolder>(){

    companion object {
        const val FILTER_ITEM = "FILTER_ITEM"
        const val RATING_ITEM = "RATING_ITEM"
    }

    private val mViewModel: HashMap<String, FilterByDestinationViewModel> = HashMap()
    private var mSelectedPaymentIndex = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ListConciergeFilterProductCriteriaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder.Builder(parent, R.layout.list_concierge_filter_product_criteria_item).build();
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val mItem = list[position]
        val viewModel = FilterByDestinationViewModel(mType != FILTER_ITEM, mItem) {}
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

    class ViewHolder(private val binding: ListConciergeFilterProductCriteriaItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val getBinding = binding

        fun onBinding(item : LocationModel, mType : String, onClickListener : OnClickCallBackLister) {

            val viewModel =
                FilterByDestinationViewModel(mType != FILTER_ITEM, item) {
                    onClickListener.onClickCallBack(item)
                }
            binding.setVariable(BR.viewModel, viewModel)
        }
    }

    interface OnClickCallBackLister{
        fun onClickCallBack(value : LocationModel)
    }

    fun updateProductOrderCounter(ids: String) {
        for (i in list.indices) {

            val indexI: Int = if (list[i].isClicked) i else 0
            val indexJ: Int = if (list[i].id == ids) i else 0

            list[i].isClicked = list[i].id == ids

            notifyItemChanged(indexI)

            notifyItemChanged(indexJ)
        }
    }
}