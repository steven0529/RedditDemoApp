package com.chuck.keddit.commons

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Steven Reyes on 09/11/2017
 */
interface ViewTypeDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}