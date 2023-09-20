package com.eazy.stcbusiness.ui.tours_guide.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.TourItemServiceModel
import com.eazy.stcbusiness.ui.todo_ui.adapter.BindingViewHolder
import com.eazy.stcbusiness.view_model.TourGuideServiceItemViewModel

class TourGuideItemServiceAdapter(
    private val list: ArrayList<TourItemServiceModel>,
    private val mOnClick: (HashMap<String, TourGuideServiceItemViewModel>) -> Unit
) :
    RecyclerView.Adapter<BindingViewHolder>() {

    private val mViewModel: HashMap<String, TourGuideServiceItemViewModel> = HashMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder.Builder(parent, R.layout.tour_guide_item_service_layout).build()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val mItem = list[position]
        val viewModel = TourGuideServiceItemViewModel(holder.context, mItem) {}
        holder.setVariable(BR.viewModel, viewModel)

        holder.binding.root.setOnClickListener {
            if (viewModel.getSelected().get()) {
                viewModel.setSelected(false)
                mItem.isSelectedItem = false
            } else {
                viewModel.setSelected(true)
                mItem.isSelectedItem = true
            }

            mOnClick.invoke(mViewModel)
        }

        mViewModel[mItem.id ?: ""] = viewModel

    }

}