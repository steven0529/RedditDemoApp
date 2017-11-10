package com.steven.redditdemoapp.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.steven.redditdemoapp.R
import com.steven.redditdemoapp.base.BaseMvpActivity
import com.steven.redditdemoapp.commons.extensions.getDisplayScore
import com.steven.redditdemoapp.commons.extensions.getRelativeTime
import com.steven.redditdemoapp.commons.extensions.loadImg
import com.steven.redditdemoapp.detail.adapter.CommentsAdapter
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rvComments.isNestedScrollingEnabled = false;

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override var mPresenter: DetailsPresenter = DetailsPresenter()

    override fun displayDetail(newsItem: NewsItem) {
        container.visibility = View.VISIBLE
        loader.visibility = View.GONE
        iv_divider.visibility = View.VISIBLE
        tv_score.text = newsItem.score.getDisplayScore(newsItem.score)
        tv_desc.text = newsItem.title
        tv_subreddit.text = "/r/${subreddit}"
        tv_submitted_by.text = "submitted ${newsItem.created.getRelativeTime()} by ${newsItem.author}"

        if (newsItem.bigImageUrl == "")
            iv_thumbnail.visibility = View.GONE
        else
            iv_thumbnail.loadImg(newsItem.bigImageUrl)
    }

    override fun displayComments(commentList: CommentList) {
        if (rvComments.adapter == null) {
            rvComments.adapter = CommentsAdapter(commentList.data.toMutableList())
        }

        rvComments.setHasFixedSize(true) // use this setting to improve performance
        var llManager = LinearLayoutManager(this)
        rvComments.layoutManager = llManager;
        var itemDecoration = DividerItemDecoration(this, llManager.orientation)
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_divider))
        rvComments.addItemDecoration(itemDecoration)

        (rvComments.adapter as CommentsAdapter).addComments(commentList.data)
        rvComments.adapter.notifyDataSetChanged()
    }

    override fun showError() {
        tv_failed_to_load.visibility = View.VISIBLE
        container.visibility = View.GONE
        rvComments.visibility = View.GONE
        loader.visibility = View.GONE
    }
}