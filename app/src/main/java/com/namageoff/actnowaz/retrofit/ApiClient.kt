package com.artifactslab.newspan.retrofit

import com.namageoff.actnowaz.features.main.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {
    @GET("actnowaz.json")
    fun getRepositories(): Call<List<NewsResponse>>
}

fun apiClient() : ApiClient = retrofit().create(ApiClient::class.java)
