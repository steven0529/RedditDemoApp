package com.steven.redditdemoapp.http.util

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.internal.http.HttpHeaders
import java.io.IOException
import java.nio.charset.Charset

/**
 * Created by Steven Reyes on 13/11/2017
 */
class RedditRepliesJsonKeyInterceptor : Interceptor {
    private val UTF8 = Charset.forName("UTF-8")

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            throw e
        }

        val responseBody = response.body()
            if (!HttpHeaders.hasBody(response)) {
            } else if (bodyEncoded(response.headers())) {
            } else {
                val source = responseBody!!.source()
                source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
                val buffer = source.buffer()

                var charset: Charset? = UTF8
                val contentType = responseBody!!.contentType()
                if (contentType != null) {
                    charset = contentType!!.charset(UTF8)
                }

                val updatedReplyJson = buffer.clone().readString(charset!!)
                        .replace("\"replies\": \"\",", "\"replies\": {},")

                val body = ResponseBody.create(contentType, updatedReplyJson)

                return response.newBuilder().body(body).build()
            }

        return response
    }

    private fun bodyEncoded(headers: Headers): Boolean {
        val contentEncoding = headers.get("Content-Encoding")
        return contentEncoding != null && !contentEncoding!!.equals("identity", ignoreCase = true)
    }
}