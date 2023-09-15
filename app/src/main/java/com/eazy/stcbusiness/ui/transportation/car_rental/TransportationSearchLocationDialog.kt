package com.eazy.stcbusiness.ui.transportation.car_rental

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.SampleBaseDialogFragment
import com.eazy.stcbusiness.databinding.FragmentTransportationSearchLocationDialogBinding
import com.eazy.stcbusiness.model.ItemRecentSearchModel
import com.eazy.stcbusiness.ui.happening_ui.adapter.HappeningItemRecentSearchAdapter

class TransportationSearchLocationDialog : SampleBaseDialogFragment<FragmentTransportationSearchLocationDialogBinding>() {

    override val layoutResource: Int = R.layout.fragment_transportation_search_location_dialog
    private var mOnClickCallBackListener : OnClickCallBackListener ?= null

    companion object {
        fun gotoTransportationSearchLocationDialog(supportFragmentManager : FragmentManager, mOnClickCallBackListener: OnClickCallBackListener) {
            val mTransportationSearchLocationDialog =
                TransportationSearchLocationDialog()
            mTransportationSearchLocationDialog.initListener(mOnClickCallBackListener)
            mTransportationSearchLocationDialog.show(supportFragmentManager, mTransportationSearchLocationDialog::class.java.name)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Search Data
        val list = ArrayList<ItemRecentSearchModel>()
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))
        list.add(ItemRecentSearchModel("angkor", "Angkor Thom", "CVQ6+5P, Krong Siem Reap"))

        mBinding.categoryRecycle.apply {
            layoutManager = LinearLayoutManager(mActivity)
            adapter = HappeningItemRecentSearchAdapter(list) {
                if (mOnClickCallBackListener != null) {
                    mOnClickCallBackListener?.onCallBackItemListener(it)
                    dismiss()
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val margin = 16
            val inset = InsetDrawable(ColorDrawable(Color.TRANSPARENT), margin)
            dialog.window!!.setBackgroundDrawable(inset)
        }
    }

    fun initListener(mOnClickCallBackListener: OnClickCallBackListener) {
        this.mOnClickCallBackListener = mOnClickCallBackListener
    }

    interface OnClickCallBackListener {
        fun onCallBackItemListener(mItemRecentSearchModel : ItemRecentSearchModel)
    }
}