package com.chuck.keddit.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.chuck.keddit.commons.ViewType
import com.chuck.keddit.commons.ViewTypeDelegateAdapter
import com.steven.redditdemoapp.R
import com.steven.redditdemoapp.commons.extensions.inflate

/**
 * Created by Steven Reyes on 09/11/2017
 */

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_news_loading)) {
    }
}