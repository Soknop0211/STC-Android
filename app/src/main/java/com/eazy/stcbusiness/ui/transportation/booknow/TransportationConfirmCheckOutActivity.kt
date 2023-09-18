package com.eazy.stcbusiness.ui.transportation.booknow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.databinding.ActivityTrasportationConfirmCheckOutBinding
import com.eazy.stcbusiness.databinding.CustomTextLeftRightDisplayLayoutBinding
import com.eazy.stcbusiness.databinding.CustomTextViewLayoutBinding
import com.eazy.stcbusiness.databinding.SuggestedRideCarRentalLayoutBinding
import com.eazy.stcbusiness.databinding.TransportationItemTypeLayoutBinding
import com.eazy.stcbusiness.model.*
import com.eazy.stcbusiness.ui.happening_ui.HappeningAddPeopleBottomSheetFragment
import com.eazy.stcbusiness.ui.happening_ui.adapter.LocalPaymentMethodAdapter
import com.eazy.stcbusiness.ui.transportation.booknow.TransportationProgressActivity.Companion.gotoTransportationProgressActivity
import com.eazy.stcbusiness.ui.transportation.car_rental.TransportationAskQuestionBottomSheetFragment
import com.eazy.stcbusiness.utils.FormatPriceHelper
import com.eazy.stcbusiness.view_model.HappeningNowCheckOutViewModel
import com.eazy.stcbusiness.view_model.OnCheckOutCallBackListener
import com.eazy.stcbusiness.view_model.SelectTypeBottomSheetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransportationConfirmCheckOutActivity : BaseActivity<ActivityTrasportationConfirmCheckOutBinding, HappeningNowCheckOutViewModel>(),
    OnCheckOutCallBackListener {

    override val layoutId = R.layout.activity_trasportation_confirm_check_out
    override val mViewModel: HappeningNowCheckOutViewModel by viewModels()

    companion object {
        fun gotoTransportationConfirmCheckOutActivity(activity: Context, mTransportationTypeModel: TransportationTypeModel){
            val intent = Intent(activity, TransportationConfirmCheckOutActivity::class.java)
            intent.putExtra(TRANSPORTATION_DATA, mTransportationTypeModel)
            activity.startActivity(intent)
        }

        fun gotoTransportationConfirmCheckOutActivity(activity: Context,
                                                      mDataTaxi : CarRentalSuggestedRideModel,
                                                      mServiceOptionList: ArrayList<CarRentalRecommendModel>){
            val intent = Intent(activity, TransportationConfirmCheckOutActivity::class.java)
            intent.putExtra(TransportationAskQuestionBottomSheetFragment.CAR_RENTAL_DATA, mDataTaxi)
            intent.putExtra(TransportationAskQuestionBottomSheetFragment.ADD_OPTION_SERVICE, mServiceOptionList)
            activity.startActivity(intent)
        }

        private const val TRANSPORTATION_DATA = "TRANSPORTATION_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        onLinePayment()
    }

    private fun initViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)


        /*** BOOK NOW && SCHEDULED **/
        val mItem = getDataFromModelClass<TransportationTypeModel>(TRANSPORTATION_DATA, this)

        val mItemBinding : TransportationItemTypeLayoutBinding = mBinding.itemLayoutTaxi
        val viewModel = SelectTypeBottomSheetViewModel(this, mItem ?: TransportationTypeModel()) {}
        mItemBinding.setVariable(BR.viewModel, viewModel)

        // Handle Layout
        mBinding.itemLayoutTaxi.mainLayout.setBackgroundResource(R.color.transparent)

        /*** CAR RENTAL ****/
        // init data
        val mDataTaxi = getDataFromModelClass<CarRentalSuggestedRideModel>(
            TransportationAskQuestionBottomSheetFragment.CAR_RENTAL_DATA, this)

        val mAddOnServiceList = getIntentList<CarRentalRecommendModel>(
            TransportationAskQuestionBottomSheetFragment.ADD_OPTION_SERVICE, this)

        val mItemCarRentalBinding : SuggestedRideCarRentalLayoutBinding = mBinding.itemLayoutCarRental
        if (mDataTaxi != null) {
            // Update Price
            mViewModel.updateTotalPrice(mDataTaxi.price ?: 0.0, mAddOnServiceList) // Calculate Price

            mViewModel.initDataCarTaxi(this, mItemCarRentalBinding, mDataTaxi)
        }

        mAddOnServiceList.forEach {
            addOptionPrice(it, this)
        }

        if (mDataTaxi != null) {
            mViewModel.setIsCarRental(true)
            mViewModel.setIsHaveAddOnService(mAddOnServiceList.isNotEmpty())
        } else {
            mViewModel.setIsCarRental(false)
        }

    }

    private fun addOptionPrice(mItem : CarRentalRecommendModel, mContext: Context){
        val binding: CustomTextLeftRightDisplayLayoutBinding = CustomTextLeftRightDisplayLayoutBinding
            .inflate(LayoutInflater.from(mContext))
        binding.nameTxt.text = mItem.name
        binding.priceTxt.text = FormatPriceHelper.getDisplayPrice(mContext, "USD", mItem.price ?: 0.0)
        mBinding.optionPrice.addView(binding.root)
    }

    private fun onLinePayment(){
        val mList = getListOnlinePayment(this@TransportationConfirmCheckOutActivity, false)
        val mListCard = getListOnlinePayment(this@TransportationConfirmCheckOutActivity, true)

        if (mList.isNotEmpty()) mList[0].isClick = true
        mBinding.recyclerView.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@TransportationConfirmCheckOutActivity)
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
            layoutManager = LinearLayoutManager(this@TransportationConfirmCheckOutActivity)
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

    override fun onAddPeople() {

    }

    override fun onAddNot() {
        val mDialog = AddNoteBottomSheetFragment.newInstance(mViewModel.getNoteAdded().get())
        mDialog.initListener(object : AddNoteBottomSheetFragment.OnClickCallBackListener {
            override fun onCallBackItemListener(mText: String) {
                mViewModel.setNoteAdded(mText)
            }

        })
        mDialog.show(supportFragmentManager, HappeningAddPeopleBottomSheetFragment::class.java.name)
    }

    override fun onClickCallBack() {
        gotoTransportationProgressActivity(this)
    }

}