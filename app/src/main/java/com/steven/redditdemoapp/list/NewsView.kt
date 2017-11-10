package com.steven.redditdemoapp.list

import com.steven.redditdemoapp.base.BaseMvpView
import com.steven.redditdemoapp.model.NewsList

/**
 * Created by Steven Reyes on 09/11/2017
 */
interface NewsView: BaseMvpView {

    fun displayNewsList(newsList: NewsList)

    fun showError()
}