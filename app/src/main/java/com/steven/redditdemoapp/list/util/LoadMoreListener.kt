package com.steven.redditdemoapp.list.util

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

/**
 * Created by Steven Reyes on 09/11/2017
 */
class LoadMoreListener(
        val loadMore: () -> Unit,
        val llManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var isLoading = true

    private var previousTotal = 0
    private var visibleThreshold = 2
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = llManager.itemCount
            firstVisibleItem = llManager.findFirstVisibleItemPosition()

            if (isLoading) {
                if (totalItemCount > previousTotal) {
                    isLoading = false
                    previousTotal = totalItemCount
                }
            }
            if (!isLoading && (totalItemCount - visibleItemCount)
                    <= (firstVisibleItem + visibleThreshold)) {
                loadMore()
                isLoading = true
                // End has been reached
                Log.i("LoadMoreListener", "No more news to load")
            }
        }
    }
}