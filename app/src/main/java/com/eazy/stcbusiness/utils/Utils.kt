package com.eazy.stcbusiness.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView

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

        fun getUrlFromResource(resourceId: Int, packetName: String): String {
            return Uri.parse("android.resource://$packetName/$resourceId").toString()
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

        // Layout manager
        fun spanGridImageLayoutManager(list: ArrayList<String>, context: Context): RecyclerView.LayoutManager {    // Image Layout View
            var gridLayoutManager = GridLayoutManager(context, 6) //show only maximum 5 images
            if (list.size < 5) {
                if (list.size % 2 == 0) { // 2 or 4 images size
                    gridLayoutManager = GridLayoutManager(context, 2)
                } else {
                    if (list.size < 5) {
                        gridLayoutManager = GridLayoutManager(context, 2)
                        gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                return if (position % 3 == 0) {
                                    2 // Take SpanCount(2) / 2 = 1 so result 1 column per row
                                } else {
                                    1 // Take SpanCount(2) / 1 = 2 so result 2 column per row
                                }
                            }
                        }
                    } else {
                        gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                return if (position == 0 || position == 1) {
                                    3 // Take SpanCount(6) / 3 = 2 so result 2 column per row
                                } else if (position == 2 || position == 3 || position == 4) {
                                    2 // Take SpanCount(6) / 2 = 3 so result 3 column per row
                                } else {
                                    3 // Take SpanCount(6) / 3 = 2 so result 2 column per row
                                }
                            }
                        }
                    }
                }
            } else {
                gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position == 0 || position == 1) {
                            3 // Take SpanCount(6) / 3 = 2 so result 2 column per row
                        } else if (position == 2 || position == 3 || position == 4) {
                            2 // Take SpanCount(6) / 2 = 3 so result 3 column per row
                        } else {
                            3 // Take SpanCount(6) / 3 = 2 so result 2 column per row
                        }
                    }
                }
            }
            return gridLayoutManager
        }

    }
}