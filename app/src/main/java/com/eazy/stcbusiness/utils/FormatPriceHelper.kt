package com.eazy.stcbusiness.utils

import android.content.Context
import com.eazy.stcbusiness.R
import java.math.BigDecimal
import java.util.*

class FormatPriceHelper {
    companion object {
        fun addPrice(originalValue: Double, addValue: Double): Double {
            val value1 = BigDecimal(originalValue)
            val value2 = BigDecimal(addValue)
            return value1.add(value2).toDouble()
        }

        // Convert Price
        fun getDisplayPrice(mContext: Context, currency : String, price: Double): String {
            val newPrice: Double = roundPriceValueAsDouble(price, 2)
            //Currently we manage with "$" currency format display only
            //Ex: 10.78$
            return mContext.getString(
                R.string.concierge_price_display_format,
                String.format(
                    Locale.ENGLISH,
                    "%.2f",
                    newPrice
                ),
                currency
            )
        }

        fun getDisplayPrice(mContext: Context, price: Double, currency : String): String {
            val newPrice: Double = roundPriceValueAsDouble(price, 2)
            //Currently we manage with "$" currency format display only
            //Ex: $10.78
            return mContext.getString(
                R.string.concierge_price_display_format,
                currency,
                String.format(
                    Locale.ENGLISH,
                    "%.2f",
                    newPrice
                )
            )
        }

        private fun roundPriceValueAsDouble(value: Double, precious: Int): Double {
            return BigDecimal(value).setScale(precious, BigDecimal.ROUND_HALF_EVEN).toDouble()
        }
    }
}