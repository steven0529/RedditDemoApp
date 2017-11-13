package com.steven.redditdemoapp.list

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chuck.keddit.news.adapter.NewsAdapter
import com.steven.redditdemoapp.R
import com.steven.redditdemoapp.base.BaseMvpActivity
import com.steven.redditdemoapp.commons.NewsType
import com.steven.redditdemoapp.list.util.LoadMoreListener
import com.steven.redditdemoapp.model.NewsList
import kotlinx.android.synthetic.main.activity_main.*

class NewsActivity : BaseMvpActivity<NewsView, NewsPresenter>(), NewsView {

    val LIMIT = "10"

    private val rvPosts by lazy {
        rv_posts
    }

    private var newsType = NewsType.HOT

    private var mRedditNewsList: NewsList? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_hot -> {
                initAdapter()
                newsType = NewsType.HOT
                rvPosts.visibility = View.GONE
                loader.visibility = View.VISIBLE
                mPresenter.loadHotNews("0", "10")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_new -> {
                initAdapter()
                newsType = NewsType.NEW
                rvPosts.visibility = View.GONE
                loader.visibility = View.VISIBLE
                mPresenter.loadNewNews("0", "10")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_random -> {
                initAdapter()
                newsType = NewsType.RANDOM
                rvPosts.visibility = View.GONE
                loader.visibility = View.VISIBLE
                mPresenter.loadRandomNews("0", "10")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        initAdapter()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.loadHotNews("0", "10")
        newsType = NewsType.HOT
    }

    override var mPresenter: NewsPresenter = NewsPresenter()

    fun initAdapter() {
        rvPosts.adapter = NewsAdapter()
        rvPosts.setHasFixedSize(true) // use this setting to improve performance
        var llManager = LinearLayoutManager(this)
        rvPosts.layoutManager = llManager;
        rvPosts.addOnScrollListener(LoadMoreListener( {loadMoreNews() }, llManager))
        var itemDecoration = DividerItemDecoration(this, llManager.orientation)
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_divider))
        rvPosts.addItemDecoration(itemDecoration)
    }

    override fun displayNewsList(newsList: NewsList) {
        loader.visibility = View.GONE
        tv_failed_to_load_posts.visibility = View.GONE
        rvPosts.visibility = View.VISIBLE
        this.mRedditNewsList = newsList
        (rvPosts.adapter as NewsAdapter).addNews(newsList.news)
        rvPosts.adapter.notifyDataSetChanged()
    }

    private fun loadMoreNews() {
        mRedditNewsList?.after?.let {
            if (newsType ==  NewsType.HOT) {
                mPresenter.loadHotNews(mRedditNewsList!!.after, LIMIT )
            } else if (newsType == NewsType.NEW) {
                mPresenter.loadNewNews(mRedditNewsList!!.after, LIMIT )
            } else if (newsType == NewsType.RANDOM) {
                mPresenter.loadRandomNews(mRedditNewsList!!.after, LIMIT )
            }
        }
    }

    override fun showError() {
        tv_failed_to_load_posts.visibility = View.VISIBLE
        loader.visibility = View.GONE
        rvPosts.visibility = View.GONE
    }
}
