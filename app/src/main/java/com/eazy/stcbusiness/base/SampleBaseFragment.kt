package com.eazy.stcbusiness.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.fragment.app.Fragment

open class SampleBaseFragment : Fragment() {

    var mActivity: Activity? = null

    val activityLauncher : BetterActivityResult<Intent, ActivityResult> = BetterActivityResult.registerActivityForResult(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (mActivity == null && context is SampleBaseActivity) {
            mActivity = context
        }
    }

}