package com.steven.redditdemoapp.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.steven.redditdemoapp.R
import kotlinx.android.synthetic.main.activity_main.*

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var llManager = LinearLayoutManager(this)
        rv_posts.layoutManager = llManager;
    }
}
