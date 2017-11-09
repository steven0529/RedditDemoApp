package com.steven.redditdemoapp.http.model

/**
 * Created by Steven Reyes on 09/11/2017
 */

class NewsDataResponse(
        val children: List<NewsChildrenResponse>,
        val after: String?,
        val before: String?
)
