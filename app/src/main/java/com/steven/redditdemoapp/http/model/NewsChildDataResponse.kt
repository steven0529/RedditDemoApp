package com.steven.redditdemoapp.http.model

/**
 * Created by Steven Reyes on 07/11/2017
 */

class NewsChildDataResponse(
        val id: String,
        val author: String,
        val title: String,
        val num_comments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String,
        val subreddit: String,
        val body: String
)