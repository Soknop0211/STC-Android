package com.eazy.stcbusiness.ui.thing_to_do

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.eazy.stcbusiness.base.SampleBaseActivity
import com.eazy.stcbusiness.databinding.ActivityThingToDoBinding

class ThingToDoActivity : SampleBaseActivity()  {


    companion object {
        fun gotoThingToDoActivity(activity: Context){
            val intent = Intent(activity, ThingToDoActivity::class.java)
            activity.startActivity(intent)
        }
    }


    private lateinit var binding : ActivityThingToDoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThingToDoBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}