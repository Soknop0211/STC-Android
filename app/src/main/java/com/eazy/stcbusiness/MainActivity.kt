package com.eazy.stcbusiness

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.eazy.stcbusiness.base.SampleBaseActivity
import com.eazy.stcbusiness.ui.home.HomeFragment
import com.eazy.stcbusiness.ui.home.MapFragment
import com.eazy.stcbusiness.ui.home.MyOrderFragment
import com.eazy.stcbusiness.ui.home.ProfileFragment
import com.eazy.stcbusiness.ui.todo_ui.fragment.DestinationLocationBottomSheetFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : SampleBaseActivity() {

    companion object {
        fun gotoMainActivity(activity: Activity){
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
            activity.finishAffinity()
        }
    }

    private lateinit var active : Fragment
    private var fragmentManager: FragmentManager = supportFragmentManager
    private lateinit var mSearchFragment: Fragment
    private lateinit var mMapFragment: Fragment
    private lateinit var mMyOrderFragment: Fragment
    private lateinit var mProfileFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAction()

//        val mDestinationBottomSheet = DestinationLocationBottomSheetFragment.newInstance("")
//        mDestinationBottomSheet.show(supportFragmentManager, DestinationLocationBottomSheetFragment::class.java.name)


    }

    @SuppressLint("WrongViewCast")
    private fun initAction(){
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationBar)

        setFragment()

        navView.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.mSearch -> {
                    replaceFragment(mSearchFragment as HomeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.mMap ->{
                    replaceFragment(mMapFragment as MapFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.mMyBooking -> {
                    replaceFragment(mMyOrderFragment as MyOrderFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.mProfile -> {
                    replaceFragment(mProfileFragment as ProfileFragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }


        // OnBack Press
        // onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setFragment(){
        mSearchFragment = HomeFragment()
        mMapFragment = MapFragment()
        mMyOrderFragment = MyOrderFragment()
        mProfileFragment = ProfileFragment()
        active = mSearchFragment

        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, mSearchFragment, HomeFragment::class.java.name).show(mSearchFragment).commit()
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, mMapFragment, MapFragment::class.java.name).hide(mMapFragment).commit()
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, mMyOrderFragment, MyOrderFragment::class.java.name).hide(mMyOrderFragment).commit()
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, mProfileFragment,  ProfileFragment::class.java.name).hide(mProfileFragment).commit()

    }

    private fun replaceFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }


}