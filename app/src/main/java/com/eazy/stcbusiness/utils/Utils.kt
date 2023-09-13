package com.eazy.stcbusiness.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
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

        // Location
        fun getStringAddress(context: Context, latitude: Double?, longitude: Double?): String {
            var addr = ""
            if (latitude != null && longitude != null) {
                val addresses: List<Address>
                if (latitude != 0.0 && longitude != 0.0) {
                    try {
                        val geocoder = Geocoder(context)
                        addresses = geocoder.getFromLocation(latitude, longitude, 1)!!
                        if (addresses.isNotEmpty()) {
                            val address = addresses[0]
                            try {
                                addr = address.getAddressLine(0)
                            } catch (ignored: java.lang.IllegalArgumentException) {
                            }
                            if (TextUtils.isEmpty(addr)
                                && !TextUtils.isEmpty(address.featureName)
                                && !TextUtils.isEmpty(address.subAdminArea)
                                && !TextUtils.isEmpty(address.adminArea)
                            ) {
                                addr = address.featureName + ", " + address.subAdminArea + ", " + address.adminArea
                            }
                        }
                    } catch (ex: java.lang.Exception) {
                        Toast.makeText(context, ex.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }
            }
            return addr
        }
    }
}