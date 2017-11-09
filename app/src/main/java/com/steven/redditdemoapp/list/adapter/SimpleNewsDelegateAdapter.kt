package com.chuck.keddit.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.chuck.keddit.commons.ViewType
import com.chuck.keddit.commons.ViewTypeDelegateAdapter
import com.steven.redditdemoapp.R
import com.steven.redditdemoapp.commons.extensions.getRelativeTime
import com.steven.redditdemoapp.commons.extensions.inflate
import com.steven.redditdemoapp.commons.extensions.loadImg
import com.steven.redditdemoapp.model.NewsItem
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * Created by Steven Reyes on 09/11/2017
 */

class SimpleNewsDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as NewsItem)
    }

    class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_news)) {

        fun bind(item: NewsItem) = with(itemView) {
            tv_score.text = "${item.score}"
            iv_thumbnail.loadImg(item.thumbnail)
            tv_desc.text = item.title
            tv_author.text = "submitted by ${item.author}"

            if (item.numComments == 1)
                tv_comments.text = "${item.numComments} comment"
            else
                tv_comments.text = "${item.numComments} comments"

            tv_relative_time.text = item.created.getRelativeTime()
        }
    }
}