package com.eazy.stcbusiness.ui.happening_ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.databinding.ActivityHappeningNowCheckOutBinding
import com.eazy.stcbusiness.databinding.CustomTextViewLayoutBinding
import com.eazy.stcbusiness.model.LocalPaymentModel
import com.eazy.stcbusiness.model.UserPeopleModel
import com.eazy.stcbusiness.ui.happening_ui.adapter.LocalPaymentMethodAdapter
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import com.eazy.stcbusiness.view_model.HappeningNowCheckOutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappeningNowCheckOutActivity : BaseActivity<ActivityHappeningNowCheckOutBinding, HappeningNowCheckOutViewModel>(),
    OnClickCallBackListener {
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
        val mList = getListOnlinePayment(this@HappeningNowCheckOutActivity, false)
        val mListCard = getListOnlinePayment(this@HappeningNowCheckOutActivity, true)

        mBinding.recyclerView.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@HappeningNowCheckOutActivity)
            adapter = LocalPaymentMethodAdapter(mList, false){
                // Set Payment Global
                for (mItem in mListCard) {
                    mItem.isClick = false
                }
                mBinding.recyclerView.adapter?.notifyDataSetChanged()

                // Reset Payment Local
                for (mItem in mList) {
                    mItem.isClick = mItem.id == it.id
                }
                mBinding.recyclerViewGlobal.adapter?.notifyDataSetChanged()
            }
        }


        mBinding.recyclerViewGlobal.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@HappeningNowCheckOutActivity)
            adapter = LocalPaymentMethodAdapter(mListCard, true){
                // Reset Payment Local
                for (mItem in mList) {
                    mItem.isClick = false
                }
                mBinding.recyclerView.adapter?.notifyDataSetChanged()

                // Set Payment Globle
                for (mItem in mListCard) {
                    mItem.isClick = mItem.id == it.id
                }
                mBinding.recyclerViewGlobal.adapter?.notifyDataSetChanged()
            }
        }

        // Add People
        addPeople(UserPeopleModel(firstName = "Dara", lastName = "Peseth"), this)
    }

    private fun addPeople(mUserPeopleModel : UserPeopleModel, mContext: Context){
        val binding: CustomTextViewLayoutBinding = CustomTextViewLayoutBinding
            .inflate(LayoutInflater.from(mContext))
        binding.txt.text = String.format("%s %s", mUserPeopleModel.firstName, mUserPeopleModel.lastName)
        mBinding.peopleAdded.addView(binding.root)
    }

    private fun getListOnlinePayment(context: Context, isCard : Boolean): ArrayList<LocalPaymentModel> {
        val homeViewModels: ArrayList<LocalPaymentModel> =ArrayList()
        if (isCard) {
            homeViewModels.add(
                LocalPaymentModel(
                    "global_payment",
                    ResourcesCompat.getDrawable(context.resources, R.drawable.card_payment, null),
                    "Caredit/Debit Card", "Tap to pay with Credit/Debit Card", false
                )
            )

        } else {
            homeViewModels.add(
                LocalPaymentModel(
                    "kess_pay",
                    ResourcesCompat.getDrawable(context.resources, R.drawable.acleda_logo, null),
                    "Kess Pay", "Tap to pay with Kess Pay", false
                )
            )

            homeViewModels.add(
                LocalPaymentModel(
                    "prince_pay",
                    ResourcesCompat.getDrawable(context.resources, R.drawable.acleda_logo, null),
                    "Prince Pay", "Tap to pay with Prince Pay", false
                )
            )

            homeViewModels.add(
                LocalPaymentModel(
                    "acleda_bank",
                    ResourcesCompat.getDrawable(context.resources, R.drawable.acleda_logo, null),
                    "Acleda Bank", "Tap to pay with Acleda Bank", false
                )
            )

            homeViewModels.add(
                LocalPaymentModel(
                    "wing_bank",
                    ResourcesCompat.getDrawable(context.resources, R.drawable.acleda_logo, null),
                    "Wing Bank", "Tap to pay with Wing Bank", false
                )
            )
        }
        return homeViewModels
    }

    override fun onClickCallBack() {
        val mDialog = HappeningAddPeopleBottomSheetFragment()
        mDialog.initListener(object : HappeningAddPeopleBottomSheetFragment.OnClickCallBackListener {
            override fun onClickSelectLocation(mUserPeopleModel : UserPeopleModel) {
                addPeople(mUserPeopleModel, this@HappeningNowCheckOutActivity)
            }

        })
        mDialog.show(supportFragmentManager, HappeningAddPeopleBottomSheetFragment::class.java.name)
    }


}