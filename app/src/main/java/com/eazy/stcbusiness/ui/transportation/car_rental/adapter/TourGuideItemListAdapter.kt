package com.eazy.stcbusiness.ui.transportation.car_rental.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.TourGuideItemModel
import com.eazy.stcbusiness.ui.todo_ui.adapter.BindingViewHolder

class TourGuideItemListAdapter(private val list : ArrayList<TourGuideItemModel>,
                               private val mOnClick: (TourGuideItemModel) -> Unit) : RecyclerView.Adapter<BindingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder.Builder(parent, R.layout.tour_guide_item_layout).build()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val mItem = list[position]
//        val viewModel = RecommendCarRentalViewModel(holder.context, mItem) {}
//        holder.setVariable(BR.viewModel, viewModel)

        holder.binding.root.setOnClickListener {
            mOnClick.invoke(mItem)
        }

    }

}