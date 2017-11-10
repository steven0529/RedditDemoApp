package com.steven.redditdemoapp.model

/**
 * Created by Steven Reyes on 10/11/2017
 */
data class CommentList(
        val after: String,
        val before: String,
        val data: List<CommentItem>)