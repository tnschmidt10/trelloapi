package com.tns.client

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.IOException




internal class Requester() {
    private val instance = TrelloAPI.getInstance()
    private val key = instance.key
    private val token = instance.token

    var client = OkHttpClient.Builder().addInterceptor(object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val newRequest: Request = chain.request().newBuilder()
                .url(chain.request().url().newBuilder().addQueryParameter("key", key).addQueryParameter("token", token).build())
                .build()
            return chain.proceed(newRequest)
        }
    }).build()

    fun <T> createRequest(api: Class<T>, type: String): T {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.trello.com/1/$type/")
            .addConverterFactory(JacksonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(api)
    }

}
