package com.eazy.stcbusiness.ui.tours_guide

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.databinding.ActivityTourGuideDetailBinding
import com.eazy.stcbusiness.databinding.ActivityTourGuideMainBinding
import com.eazy.stcbusiness.model.CustomCategoryModel
import com.eazy.stcbusiness.ui.home.HomeContentFragment
import com.eazy.stcbusiness.view_model.OnClickItemListener
import com.eazy.stcbusiness.view_model.OnOnClickItemDetailListener
import com.eazy.stcbusiness.view_model.TourGuideDetailViewModel
import com.eazy.stcbusiness.view_model.TourGuideViewModel
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.abs

@AndroidEntryPoint
class TourGuideDetailActivity : BaseActivity<ActivityTourGuideDetailBinding, TourGuideDetailViewModel>(),
    OnOnClickItemDetailListener {

    override val layoutId =  R.layout.activity_tour_guide_detail
    override val mViewModel: TourGuideDetailViewModel by viewModels()

    companion object {
        fun gotoTourGuideDetailActivity(activity: Context){
            val intent = Intent(activity, TourGuideDetailActivity::class.java)
            activity.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        //Custom app bar scroll to top
        mBinding.appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (kotlin.math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                // Collapsed
                mViewModel.setTitle(resources.getString(R.string.tour_guides))
            } else {
                mViewModel.setTitle("")
            }
        }
    }

    private fun replaceFragment(mFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_fragment, mFragment, mFragment.tag)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    private fun initViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)

        mViewModel.onItemServiceClickListener()

    }

    override fun onItemDetailClickListener() {
        replaceFragment(TourGuideItemDetailFragment())
    }

    override fun onItemServiceClickListener() {
        replaceFragment(TourGuideItemServiceFragment())
    }

    override fun onClickCallBack() {
        TODO("Not yet implemented")
    }
}