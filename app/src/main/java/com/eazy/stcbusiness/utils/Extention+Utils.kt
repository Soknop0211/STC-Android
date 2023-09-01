package com.eazy.stcbusiness.utils

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eazy.stcbusiness.R

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


fun TextView.setTextStrikeStyle() {
    this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}


fun Activity.getWidth(): Int {
    val displayMetrics = DisplayMetrics()
    this.windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}