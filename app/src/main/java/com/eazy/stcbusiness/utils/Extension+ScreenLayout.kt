package com.eazy.stcbusiness.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

fun Context.getScreenWidth(): Int {
    val displayMetrics = this.resources.displayMetrics
    return displayMetrics.widthPixels
}

fun Context.getScreenHeight(): Int {
    val displayMetrics = this.resources.displayMetrics
    val dpHeight = displayMetrics.heightPixels.toFloat()
    return dpHeight.toInt()
}

fun Context.setHeightOfScreen(numberDivide: Double): Int {
    val height: Int = this.getScreenHeight()
    return (height / numberDivide).toInt()
}

fun Activity.getWidth(): Int {
    val displayMetrics = DisplayMetrics()
    this.windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}

fun Context.dpToPx(dp: Int): Int {
    val r = this.resources
    val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics)
    return px.toInt()
}
