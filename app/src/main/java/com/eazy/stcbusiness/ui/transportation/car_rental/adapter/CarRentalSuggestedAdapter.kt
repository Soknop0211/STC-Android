package com.eazy.stcbusiness.ui.transportation.car_rental.adapter

import android.text.TextUtils
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.CarRentalSuggestedRideModel
import com.eazy.stcbusiness.ui.todo_ui.adapter.BindingViewHolder
import com.eazy.stcbusiness.utils.FormatPriceHelper
import com.eazy.stcbusiness.view_model.OnClickItemCallBackListener
import com.eazy.stcbusiness.view_model.SelectTypeBottomSheetViewModel
import com.eazy.stcbusiness.view_model.SuggestedRideCarRentalViewModel

class CarRentalSuggestedAdapter(private val list : ArrayList<CarRentalSuggestedRideModel>,
                                private val mOnClick: (CarRentalSuggestedRideModel) -> Unit) : RecyclerView.Adapter<BindingViewHolder>(){

    private val mViewModel: HashMap<String, SelectTypeBottomSheetViewModel> = HashMap()
    private var mSelectedPaymentIndex = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder.Builder(parent, R.layout.suggested_ride_car_rental_layout).build();
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val mItem = list[position]
        val viewModel = SuggestedRideCarRentalViewModel(holder.context, mItem, object : OnClickItemCallBackListener {
            override fun onAddItem(mCarRentalSuggestedRideModel: CarRentalSuggestedRideModel) {
                updateProductCount(mCarRentalSuggestedRideModel)
            }

            override fun onMinusItem(mCarRentalSuggestedRideModel: CarRentalSuggestedRideModel) {
                updateProductCount(mCarRentalSuggestedRideModel)
            }

            override fun onClickViewDetail(mCarRentalSuggestedRideModel: CarRentalSuggestedRideModel) {
                mOnClick.invoke(mCarRentalSuggestedRideModel)
            }

        })
        holder.setVariable(BR.viewModel, viewModel)

//        holder.binding.root.setOnClickListener {
//            viewModel.setSelected(true)
//            unselectOption(mItem.id ?: "")
//
//            mOnClick.invoke(mItem)
//        }
//
//        mViewModel[mItem.id ?: ""] = viewModel
//
//        if (position == mSelectedPaymentIndex) {
//            holder.binding.root.performClick()
//            mSelectedPaymentIndex = -1
//        }
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

    private fun updateProductCount(mItem: CarRentalSuggestedRideModel) {
        for (data in list) {
            if (TextUtils.equals(mItem.id, data.id)) {
                data.mItemCount = mItem.mItemCount
                break
            }
        }
    }

    fun getTotalPrice(): Double {
        var total = 0.0
        for (data in list) {
            total = FormatPriceHelper.addPrice(total, data.price ?: 0.0)
        }
        return total
    }

}