package com.steven.redditdemoapp.http.model

/**
 * Created by Steven Reyes on 09/11/2017
 */

class RedditDataResponse(
        val children: List<RedditChildrenResponse>,
        val after: String?,
        val before: String?
)
