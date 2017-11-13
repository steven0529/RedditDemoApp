package com.steven.redditdemoapp.detail

import client.yalantis.com.githubclient.mvp.BaseMvpPresenterImpl
import com.steven.redditdemoapp.commons.PrefixType
import com.steven.redditdemoapp.http.RedditNewsApiManager
import com.steven.redditdemoapp.http.model.NewsBaseResponse
import com.steven.redditdemoapp.http.model.NewsChildrenResponse
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
                    for (redditResponse in redditArrayResponse) {
                        if (redditResponse.data.children[0].kind == PrefixType.LINK) {
                            val item = redditArrayResponse[0].data.children[0].data
                            var imageUrl = ""

                            if (item.preview != null && item.preview.images[0].source.url != null)
                                imageUrl = item.preview.images[0].source.url

                            val newsItem = NewsItem(item.author, item.title!!, item.num_comments!!,
                                    item.created, item.thumbnail!!, item.url!!, item.score!!,
                                    item.subreddit!!, item.id, imageUrl)
                            mView?.displayDetail(newsItem)
                        } else if (redditResponse.data.children[0].kind == PrefixType.COMMENTS) {
                            var comments: MutableList<CommentItem> = mutableListOf()

                            for (commentItem in redditArrayResponse[1].data.children) {
                                if (commentItem.kind == PrefixType.COMMENTS) {
                                    var newsCommentItem = if (commentItem.data.replies?.data != null) {
                                        parseReplyItem(commentItem)
                                    } else {
                                        CommentItem(commentItem.data.author, commentItem.data.body!!,
                                                commentItem.data.created, commentItem.data.score)
                                    }
                                    comments.add(newsCommentItem)
                                }
                            }
                            val commentsList = CommentList(redditArrayResponse[1].data.after ?: "",
                                    redditArrayResponse[1].data.before ?: "",
                                    comments)
                            mView?.displayComments(commentsList)
                        }
                    }
                })
                { throwable ->
                    mView?.showError()
                    throwable.printStackTrace()
                }
    }

    fun parseReplyItem(replyItem: NewsChildrenResponse): CommentItem {
        replyItem.data.replies
        var comments: MutableList<CommentItem> = mutableListOf()
        for (commentItem in replyItem.data.replies?.data!!.children) {
            if (commentItem.kind == PrefixType.COMMENTS) {
                var newsCommentItem: CommentItem
                if (commentItem.data.replies?.data != null) {
                    newsCommentItem = parseReplyItem(commentItem)
                } else {
                    newsCommentItem = CommentItem(commentItem.data.author, commentItem.data.body!!,
                            commentItem.data.created, commentItem.data.score)
                }
                comments.add(newsCommentItem)
            }
        }
        if (comments.isEmpty())
            return CommentItem(replyItem.data.author, replyItem.data.body!!,
                    replyItem.data.created, replyItem.data.score)
        else
            return CommentItem(replyItem.data.author, replyItem.data.body!!,
                    replyItem.data.created, replyItem.data.score, comments)
    }

}