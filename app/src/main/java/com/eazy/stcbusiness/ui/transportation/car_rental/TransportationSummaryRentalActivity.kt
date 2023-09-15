package com.eazy.stcbusiness.ui.transportation.car_rental

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eazy.stcbusiness.R

class TransportationSummaryRentalActivity : AppCompatActivity() {

    companion object {
        fun gotoTransportationSummaryRentalActivity(activity: Context){
            val intent = Intent(activity, TransportationSummaryRentalActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transportation_summary_rental)
    }

}