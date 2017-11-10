package com.steven.redditdemoapp.detail

import client.yalantis.com.githubclient.mvp.BaseMvpPresenterImpl
import com.steven.redditdemoapp.http.RedditNewsApiManager
import com.steven.redditdemoapp.http.model.NewsBaseResponse
import com.steven.redditdemoapp.model.CommentItem
import com.steven.redditdemoapp.model.CommentList

/**
 * Created by Steven Reyes on 10/11/2017
 */
class DetailsPresenter : BaseMvpPresenterImpl<DetailsView>() {

    fun loadComments(subreddit: String, id: String) {
        RedditNewsApiManager.getComments(subreddit, id)
                .subscribe({ redditResponse: NewsBaseResponse? ->
                    redditResponse?.let {
                        redditResponse.data.children.map {
                            val comment = redditResponse.data.children.map {
                                val item = it.data
                                CommentItem(item.author, item.body, item.created)
                            }

                            val comments = CommentList(redditResponse.data.after ?: "",
                                    redditResponse.data.before ?: "",
                                    comment)

                            mView?.displayComments(comments)
                        }
                    }
                })
    }
}