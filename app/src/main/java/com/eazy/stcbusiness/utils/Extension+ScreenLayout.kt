package com.eazy.stcbusiness.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.coordinatorlayout.widget.CoordinatorLayout

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

fun widthHeightLayout(layout: View, mListener : (Int, Int) -> Unit) {
    val vto = layout.viewTreeObserver
    vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                layout.viewTreeObserver.removeGlobalOnLayoutListener(this)
            } else {
                layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
            mListener.invoke(layout.measuredWidth, layout.measuredHeight)
        }
    })
}

fun View.setMarginBottomOnCoordinatorLayout(bottom: Int) {
    val layoutParams = this.layoutParams as CoordinatorLayout.LayoutParams
    layoutParams.setMargins(0, 0, 0, bottom)
    this.layoutParams = layoutParams
}
