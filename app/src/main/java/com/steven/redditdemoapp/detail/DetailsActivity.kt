package com.steven.redditdemoapp.detail

import android.os.Bundle
import android.os.PersistableBundle
import com.steven.redditdemoapp.R
import com.steven.redditdemoapp.base.BaseMvpActivity
import com.steven.redditdemoapp.model.CommentList
import com.steven.redditdemoapp.model.NewsItem
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by Steven Reyes on 10/11/2017
 */
class DetailsActivity: BaseMvpActivity<DetailsView, DetailsPresenter>(), DetailsView {

    companion object {
        val EXTRA_SUBREDDIT = "subreddit"
        val EXTRA_ARTICLE_ID = "article_id"
    }

    private val rvComments by lazy {
        rv_comments
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_detail)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.loadComments("aww", "7atshd")
    }

    override var mPresenter: DetailsPresenter = DetailsPresenter()

    override fun displayDetail(newsItem: NewsItem) {
    }

    override fun displayComments(commentList: CommentList) {
    }
}