package com.eazy.stcbusiness.ui.transportation.booknow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import com.eazy.stcbusiness.base.SampleBaseActivity
import com.eazy.stcbusiness.databinding.ActivityTransportationProgressBinding
import com.eazy.stcbusiness.ui.transportation.bookschedule.TransportationWaitingActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TransportationProgressActivity : SampleBaseActivity() {

    companion object {
        fun gotoTransportationProgressActivity(activity: Context){
            val intent = Intent(activity, TransportationProgressActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private lateinit var txtProgress: TextView
    private lateinit var progressBar: ProgressBar
    private var pStatus = 0
    private var handler: Handler ?= null

    private lateinit var binding : ActivityTransportationProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransportationProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = binding.progressCircularId
        txtProgress = binding.txtProgress
        handler = Handler(Looper.myLooper()!!)

        pStatus = 0

//        Thread {
//            while (pStatus <= 100) {
//
//                handler?.post {
//                    progressBar.progress = pStatus
//                    txtProgress.text = String.format("%s", "$pStatus %")
//                    Log.d("jeeeeeeeeeeeeeeeeeeeeeeee", "$pStatus %")
//                }
//
//                try {
//                    Thread.sleep(200)
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                }
//
//                pStatus++
//
//            }
//        }.start()

        handler?.post(handlerRunnable)

    }

    private val handlerRunnable: Runnable = object : Runnable {
        override fun run() {
            // Your handler function logic here
            if (pStatus <= 100) {
                handler?.postDelayed(this, 200) // Post the handler again for the next execution
            } else {
                handler?.removeCallbacks(this) // Remove the pending callbacks

                TransportationWaitingActivity.gotoTransportationWaitingActivity(this@TransportationProgressActivity)
            }

            progressBar.progress = pStatus
            txtProgress.text = String.format("%s", "$pStatus %")
            pStatus++
        }
    }


    override fun onPause() {
        super.onPause()
        handler?.removeCallbacks(handlerRunnable) // Remove the pending callbacks
    }

}