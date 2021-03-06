package com.steven.redditdemoapp.http

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.steven.redditdemoapp.http.model.NewsBaseResponse
import com.steven.redditdemoapp.http.util.RedditRepliesJsonKeyInterceptor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Created by Steven Reyes on 09/11/2017
 */
object RedditNewsApiManager {

    private const val BASE_URL: String = "https://www.reddit.com/"

    private lateinit var redditApi: RedditApi

    init {
        val retrofit = initRetrofit()
        initServices(retrofit)
    }

    private fun initRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val replyInterceptor = RedditRepliesJsonKeyInterceptor()

        val client = OkHttpClient.Builder().apply {
            networkInterceptors().add(Interceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build()
                chain.proceed(request)
            })
            addInterceptor(interceptor)
            addInterceptor(replyInterceptor)
        }

        return Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(createMoshiConverter())
                .client(client.build())
                .build()
    }

    private fun createMoshiConverter(): MoshiConverterFactory = MoshiConverterFactory.create().asLenient()

    private fun initServices(retrofit: Retrofit) {
        redditApi = retrofit.create(RedditApi::class.java)
    }

    fun getHotArticles(after: String, limit: String): Observable<NewsBaseResponse> {
        return redditApi.getTopArticles(after, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())!!
    }

    fun getNewArticles(after: String, limit: String): Observable<NewsBaseResponse> {
        return redditApi.getNewArticles(after, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())!!
    }

    fun getRandomArticles(after: String, limit: String): Observable<NewsBaseResponse> {
        return redditApi.getRandomArticles(after, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())!!
    }

    fun getComments(subreddit: String, id: String): Observable<List<NewsBaseResponse>> {
        return redditApi.getComments(subreddit, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())!!
    }

}