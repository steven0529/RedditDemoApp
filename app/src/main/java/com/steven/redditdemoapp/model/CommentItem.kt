package com.steven.redditdemoapp.model

/**
 * Created by Steven Reyes on 10/11/2017
 */
data class CommentItem(
        val author: String = "",
        val body: String = "",
        val created: Long = System.currentTimeMillis(),
        val replies: MutableList<CommentItem> = mutableListOf()
)