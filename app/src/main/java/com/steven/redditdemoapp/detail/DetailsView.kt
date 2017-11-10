package com.steven.redditdemoapp.detail

import com.steven.redditdemoapp.base.BaseMvpView
import com.steven.redditdemoapp.model.CommentList
import com.steven.redditdemoapp.model.NewsItem

/**
 * Created by Steven Reyes on 10/11/2017
 */
interface DetailsView : BaseMvpView {

    fun displayDetail(newsItem: NewsItem)

    fun displayComments(commentList: CommentList)

    fun showError()
}