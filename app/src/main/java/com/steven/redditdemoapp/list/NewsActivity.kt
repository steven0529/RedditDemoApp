package com.steven.redditdemoapp.list

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chuck.keddit.news.adapter.NewsAdapter
import com.steven.redditdemoapp.R
import com.steven.redditdemoapp.base.BaseMvpActivity
import com.steven.redditdemoapp.list.util.LoadMoreListener
import com.steven.redditdemoapp.model.NewsList
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class NewsActivity : BaseMvpActivity<NewsView, NewsPresenter>(), NewsView {

    val LIMIT = "10"

    private val rvPosts by lazy {
        rv_posts
    }

    private var mRedditNewsList: NewsList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (rvPosts.adapter == null) {
            rvPosts.adapter = NewsAdapter()
        }
        rvPosts.setHasFixedSize(true) // use this setting to improve performance
        var llManager = LinearLayoutManager(this)
        rvPosts.layoutManager = llManager;
        rvPosts.addOnScrollListener(LoadMoreListener( {loadMoreNews() }, llManager))
        var itemDecoration = DividerItemDecoration(this, llManager.orientation)
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_divider))
        rvPosts.addItemDecoration(itemDecoration)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.loadNews("0", "10")
    }

    override var mPresenter: NewsPresenter = NewsPresenter()

    override fun displayNewsList(newsList: NewsList) {
        this.mRedditNewsList = newsList
        (rvPosts.adapter as NewsAdapter).addNews(newsList.news)
        rvPosts.adapter.notifyDataSetChanged()
    }

    private fun loadMoreNews() {
        mRedditNewsList?.after?.let {
            mPresenter.loadNews(mRedditNewsList!!.after, LIMIT )
        }
    }

    override fun showError() {
        tv_failed_to_load_posts.visibility = View.VISIBLE
        rvPosts.visibility = View.GONE
    }
}
