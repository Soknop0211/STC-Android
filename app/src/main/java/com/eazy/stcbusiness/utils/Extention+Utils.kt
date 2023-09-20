package com.eazy.stcbusiness.utils

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eazy.stcbusiness.R
import java.math.BigDecimal
import java.util.*

fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun View.backgroundTint(color : Int) {
    this.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this.context, color))
}

fun ImageView.initImage(value : String?) {
    Glide.with(this).load(value ?: R.drawable.stc_banner)
        .placeholder(R.drawable.stc_banner)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun ImageView.initImage(value : Drawable?) {
    Glide.with(this).load(value ?: R.drawable.stc_banner)
        .placeholder(R.drawable.stc_banner)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun TextView.initText(value : String?) {
    this.text = value ?: ". . ."
}

fun TextView.initText(resId : Int, mContext: Context) {
    this.text = mContext.getString(resId)
}

fun TextView.setTextStrikeStyle() {
    this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

// Convert Price
fun Context.getDisplayPrice(currency : String, price: Double): String {
    val newPrice: Double = roundPriceValueAsDouble(price, 2)
    //Currently we manage with "$" currency format display only
    //Ex: $10.78
    return this.getString(
        R.string.concierge_price_display_format,
        String.format(
            Locale.ENGLISH,
            "%.2f",
            newPrice
        ),
        currency
    )
}

fun Context.getDisplayPrice(price: Double, currency : String): String {
    val newPrice: Double = roundPriceValueAsDouble(price, 2)
    //Currently we manage with "$" currency format display only
    //Ex: $10.78
    return this.getString(
        R.string.concierge_price_display_format,
        currency,
        String.format(
            Locale.ENGLISH,
            "%.2f",
            newPrice
        )
    )
}
fun roundPriceValueAsDouble(value: Double, precious: Int): Double {
    return BigDecimal(value).setScale(precious, BigDecimal.ROUND_HALF_EVEN).toDouble()
}