package com.example.catapi.networking

import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val HEADER_AUTHORIZATION = "x-api-key"
private const val TOKEN_KEY = ""

fun buildClient() : OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(buildAuthorizationInterceptor())
    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    .build()

fun buildAuthorizationInterceptor() = object : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val new = originalRequest.newBuilder().addHeader(HEADER_AUTHORIZATION, TOKEN_KEY).build()
        return chain.proceed(new)
    }
}

fun buildRetrofit(parser : Moshi) : Retrofit{

    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(parser))
        .build()
}

fun buildApiService(parser: Moshi) : RemoteApiService = buildRetrofit(parser).create(RemoteApiService::class.java)

