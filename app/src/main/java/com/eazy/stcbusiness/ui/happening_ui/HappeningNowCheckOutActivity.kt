package com.eazy.stcbusiness.ui.happening_ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.base.BaseView
import com.eazy.stcbusiness.databinding.ActivityHappeningNowCheckOutBinding
import com.eazy.stcbusiness.model.LocalPaymentModel
import com.eazy.stcbusiness.ui.happening_ui.adapter.LocalPaymentMethodAdapter
import com.eazy.stcbusiness.view_model.HappeningNowCheckOutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappeningNowCheckOutActivity : BaseActivity<ActivityHappeningNowCheckOutBinding, HappeningNowCheckOutViewModel>(),
    BaseView {
    override val layoutId: Int = R.layout.activity_happening_now_check_out
    override val mViewModel: HappeningNowCheckOutViewModel by viewModels()

    companion object {
        fun gotoHappeningNowCheckOutActivity(activity: Context){
            val intent = Intent(activity, HappeningNowCheckOutActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)

        onLinePayment()
    }

    private fun onLinePayment(){
        val mList = getListOnlinePayment(this@HappeningNowCheckOutActivity)
        mBinding.recyclerView.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@HappeningNowCheckOutActivity)
            adapter = LocalPaymentMethodAdapter(mList){
                for (mItem in mList) {
                    mItem.isClick = mItem.id == it.id
                }
                mBinding.recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun getListOnlinePayment(context: Context): ArrayList<LocalPaymentModel> {
        val homeViewModels: ArrayList<LocalPaymentModel> =ArrayList()
        homeViewModels.add(
            LocalPaymentModel(
                "aba",
                ResourcesCompat.getDrawable(context.resources, R.drawable.aba_logo, null),
                "ABA Pay", "Tap to pay with aba pay", false
            )
        )
        homeViewModels.add(
            LocalPaymentModel(
                "kess",
                ResourcesCompat.getDrawable(context.resources, R.drawable.aba_logo, null),
                "Kess Pay", "Tap to pay with kess pay", false
            )
        )
        return homeViewModels
    }

}