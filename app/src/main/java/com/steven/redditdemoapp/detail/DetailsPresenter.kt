package com.steven.redditdemoapp.detail

import client.yalantis.com.githubclient.mvp.BaseMvpPresenterImpl
import com.steven.redditdemoapp.commons.PrefixType
import com.steven.redditdemoapp.http.RedditNewsApiManager
import com.steven.redditdemoapp.http.model.NewsBaseResponse
import com.steven.redditdemoapp.model.CommentItem
import com.steven.redditdemoapp.model.CommentList
import com.steven.redditdemoapp.model.NewsItem

/**
 * Created by Steven Reyes on 10/11/2017
 */
class DetailsPresenter : BaseMvpPresenterImpl<DetailsView>() {

    fun loadComments(subreddit: String, id: String) {
        RedditNewsApiManager.getComments(subreddit, id)
                .subscribe({ redditArrayResponse: List<NewsBaseResponse> ->
                    if (redditArrayResponse[0].data.children[0].kind == PrefixType.LINK) {
                        val item = redditArrayResponse[0].data.children[0].data
                        val newsItem = NewsItem(item.author, item.title!!, item.num_comments!!,
                                item.created, item.thumbnail!!, item.url!!, item.score!!,
                                item.subreddit!!, item.id)
                        mView?.displayDetail(newsItem)
                    }
                    if (redditArrayResponse[1].data.children[0].kind == PrefixType.COMMENTS) {
                        val comments = redditArrayResponse[1].data.children.map {
                            val item = it.data
                            CommentItem(item.author, item.body!!, item.created)
                        }

                        val commentsList = CommentList(redditArrayResponse[1].data.after ?: "",
                                redditArrayResponse[1].data.before ?: "",
                                comments)
                        mView?.displayComments(commentsList)
                    }
                })
    }
}