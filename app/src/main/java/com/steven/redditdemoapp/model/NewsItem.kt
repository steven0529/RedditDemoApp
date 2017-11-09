package com.steven.redditdemoapp.model

import com.chuck.keddit.commons.ViewType
import com.steven.redditdemoapp.list.util.NewsAdapterConstants

/**
 * Created by Steven Reyes on 09/11/2017
 */
data class NewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
) : ViewType {
    override fun getViewType() = NewsAdapterConstants.NEWS
}
