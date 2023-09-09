package com.eazy.stcbusiness.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class Utils {
    companion object {
        fun replaceFragment(mFragmentId : Int, mFragment : Fragment, mManager : FragmentManager) {
            val transaction = mManager.beginTransaction()
            transaction.replace(mFragmentId, mFragment, mFragment.tag)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }

    }
}