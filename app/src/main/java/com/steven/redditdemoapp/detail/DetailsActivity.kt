package com.steven.redditdemoapp.detail

import android.os.Bundle
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

    var subreddit: String? = null
    var id: String? = null

    private val rvComments by lazy {
        rv_comments
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (intent.extras.getString(EXTRA_SUBREDDIT) != null
                && intent.extras.getString(EXTRA_ARTICLE_ID) != null) {
            subreddit = intent.extras.getString(EXTRA_SUBREDDIT)
            id = intent.extras.getString(EXTRA_ARTICLE_ID)
        }
    }

    override fun onResume() {
        super.onResume()
        if (subreddit != null && id != null) {
                mPresenter.loadComments(subreddit!!, id!!)
        }
    }

    override var mPresenter: DetailsPresenter = DetailsPresenter()

    override fun displayDetail(newsItem: NewsItem) {

    }

    override fun displayComments(commentList: CommentList) {
    }
}