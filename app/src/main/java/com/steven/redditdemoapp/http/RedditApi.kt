package com.steven.redditdemoapp.http

import com.steven.redditdemoapp.http.model.NewsBaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Steven Reyes on 07/11/2017
 */

interface RedditApi {

    @GET("/top.json")
    fun getTopArticles(@Query("after") after: String, @Query("limit") limit: String) : Observable<NewsBaseResponse>

    @GET("/r/{subreddit}/comments/{id}.json")
    fun getComments(@Path("subreddit") subreddit: String, @Path("id") id: String) : Observable<List<NewsBaseResponse>>

}