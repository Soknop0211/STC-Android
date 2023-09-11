package com.eazy.stcbusiness.utils

import android.text.TextUtils
import android.util.Patterns
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

        fun isEmailAddress(email: String?): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun phoneNumberValidate(phone: String?): Boolean {
            return !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches()
        }

    }
}