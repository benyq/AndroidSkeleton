package com.benyq.core.http

import android.util.Log
import com.benyq.core.BuildConfig
import com.benyq.core.utils.Logger
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitManager {

    fun <T> createApiService(
        baseUrl: String,
        clazz: Class<T>,
        interceptors: List<Interceptor> = emptyList(),
        networkInterceptors: List<Interceptor> = emptyList()
    ): T {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Logger.d(this@RetrofitManager,"log", message)
        }.apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BASIC
            }
        }
        val okhttpClient = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            interceptors.forEach {
                addInterceptor(it)
            }
            networkInterceptors.forEach {
                addNetworkInterceptor(it)
            }
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            connectTimeout(30, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
        }.build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
        return retrofit.create(clazz)
    }

}