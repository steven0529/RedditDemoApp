package com.steven.redditdemoapp.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.steven.redditdemoapp.R
import com.steven.redditdemoapp.model.NewsList
import kotlinx.android.synthetic.main.activity_main.*

class NewsActivity : AppCompatActivity(), NewsView {

    private val rvPosts by lazy {
        rv_posts
    }

    private var redditNewsList: NewsList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var llManager = LinearLayoutManager(this)
        rv_posts.layoutManager = llManager;
    }

    override fun displayNewsList(newsList: NewsList) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
