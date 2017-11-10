package com.steven.redditdemoapp.commons.extensions

import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.steven.redditdemoapp.R

/**
 * Created by Steven Reyes on 09/11/2017
 */

fun ImageView.loadImg(imageUrl: String) {

    if (TextUtils.isEmpty(imageUrl)) {
        Glide.with(context)
                .load(R.drawable.ic_small_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(this)
    } else {
        Glide.with(context)
                .load(imageUrl)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(this)
    }

}