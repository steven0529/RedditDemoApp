package com.steven.redditdemoapp.detail.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.steven.redditdemoapp.R
import com.steven.redditdemoapp.commons.extensions.getRelativeTime
import com.steven.redditdemoapp.commons.extensions.inflate
import com.steven.redditdemoapp.model.CommentItem
import kotlinx.android.synthetic.main.item_comment.view.*

/**
 * Created by Steven Reyes on 10/11/2017
 */
class CommentsAdapter(private val comments: MutableList<CommentItem>)
    : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommentViewHolder {
        return CommentViewHolder(parent!!)
    }

    override fun onBindViewHolder(holder: CommentViewHolder?, position: Int) {
        holder as CommentViewHolder
        val item = comments.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    fun addComments(commentsList: List<CommentItem>) {
        comments.addAll(commentsList)
    }

    class CommentViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_comment)) {

        fun bind(item: CommentItem) = with(itemView) {
            tv_body.text = item.body
            tv_author.text = item.author
            tv_relative_time.text = item.created.getRelativeTime()
            if (!item.replies.isEmpty()) {

                rv_replies.adapter = CommentsAdapter(item.replies)

                rv_replies.setHasFixedSize(true) // use this setting to improve performance
                var llManager = LinearLayoutManager(context)
                rv_replies.layoutManager = llManager
            } else
                rv_replies.visibility = View.GONE
        }
    }
}