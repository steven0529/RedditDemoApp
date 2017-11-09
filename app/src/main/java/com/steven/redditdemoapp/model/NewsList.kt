package com.steven.redditdemoapp.model

/**
 * Created by Steven Reyes on 09/11/2017
 */
data class NewsList(
        val after: String,
        val before: String,
        val news: List<NewsItem>)