package com.steven.redditdemoapp.commons.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Steven Reyes on 09/11/2017
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}