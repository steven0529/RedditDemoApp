package com.steven.redditdemoapp.list

import client.yalantis.com.githubclient.mvp.BaseMvpPresenterImpl
import com.steven.redditdemoapp.http.RedditNewsApiManager
import com.steven.redditdemoapp.http.model.NewsBaseResponse
import com.steven.redditdemoapp.model.NewsItem
import com.steven.redditdemoapp.model.NewsList

/**
 * Created by Steven Reyes on 09/11/2017
 */
class NewsPresenter: BaseMvpPresenterImpl<NewsView>() {

    fun loadNews(after: String, limit: String) {
        RedditNewsApiManager.getNews(after, limit)
                .subscribe({ redditResponse: NewsBaseResponse? ->
                    redditResponse?.let {
                        redditResponse.data.children.map {
                            val news = redditResponse.data.children.map {
                                val item = it.data
                                NewsItem(item.author, item.title!!, item.num_comments!!,
                                        item.created, item.thumbnail!!, item.url!!, item.score!!,
                                        item.subreddit!!, item.id)
                            }

                            val redditNews = NewsList(redditResponse.data.after ?: "",
                                    redditResponse.data.before ?: "",
                                    news)

                            mView?.displayNewsList(redditNews)
                        }
                    }
                })
    }
}