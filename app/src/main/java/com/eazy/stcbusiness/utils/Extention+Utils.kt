package com.eazy.stcbusiness.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eazy.stcbusiness.R

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
