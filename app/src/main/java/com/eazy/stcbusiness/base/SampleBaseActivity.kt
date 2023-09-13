package com.eazy.stcbusiness.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity

open class SampleBaseActivity : AppCompatActivity() {

    val activityLauncher : BetterActivityResult<Intent, ActivityResult> = BetterActivityResult.registerActivityForResult(this)

    companion object {
        var latitude = 0.0
        var longitude = 0.0
        var country_code: String? = null
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val mActionBar = supportActionBar
        if (mActionBar != null) {
            supportActionBar?.hide()
        }
    }

    open fun getStringExtra(key: String, context: Activity) : String {
        if (context.intent != null && context.intent.hasExtra(key)) {
            return context.intent.getStringExtra(key).toString()
        }
        return ""
    }

}