package com.eazy.stcbusiness.ui.transportation.car_rental.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.CarRentalRecommendModel
import com.eazy.stcbusiness.ui.todo_ui.adapter.BindingViewHolder
import com.eazy.stcbusiness.view_model.RecommendCarRentalViewModel

class CarRentalRecommendAdapter(private val list : ArrayList<CarRentalRecommendModel>,
                                private val mOnClick: (CarRentalRecommendModel) -> Unit) : RecyclerView.Adapter<BindingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder.Builder(parent, R.layout.recommend_car_rental_layout).build()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val mItem = list[position]
        val viewModel = RecommendCarRentalViewModel(holder.context, mItem) {}
        holder.setVariable(BR.viewModel, viewModel)

        holder.binding.root.setOnClickListener {
            mOnClick.invoke(mItem)
        }

    }

}