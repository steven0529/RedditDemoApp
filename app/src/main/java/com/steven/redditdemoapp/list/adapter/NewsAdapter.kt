package com.chuck.keddit.news.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.chuck.keddit.commons.ViewType
import com.chuck.keddit.commons.ViewTypeDelegateAdapter
import com.steven.redditdemoapp.list.util.NewsAdapterConstants
import com.steven.redditdemoapp.model.NewsItem

/**
 * Created by Steven Reyes on 09/11/2017
 */
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private var items: ArrayList<ViewType>
    private val loadingItem = object : ViewType {
        override fun getViewType() = NewsAdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(NewsAdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(NewsAdapterConstants.NEWS, SimpleNewsDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }

    override fun getItemCount() = items.size

    fun addNews(news: List<NewsItem>) {
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1) // size +1 for loading view
    }

    fun clearAndReAddNews(news: List<NewsItem>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getNewsList(): List<NewsItem> {
        return items
                .filter { it.getViewType() == NewsAdapterConstants.NEWS }
                .map { it as NewsItem }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex

}