package com.gystry.frameweathertry.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * retrofit 网络请求的核心
 */
object ServiceCreator {

    /**
     * const只能修饰val
     *
     * const val BASE_URL
     * 相当于 公有的静态的不可改变的
     * private const val BASE_URL
     * 相当于私有的静态的不可改变的
     */
    private const val BASE_URL = "http://guolin.tech/"

    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = builder.build()

    /**
     * 带泛型的方法
     */
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

}