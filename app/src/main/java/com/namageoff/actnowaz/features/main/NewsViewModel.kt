package com.namageoff.actnowaz.features.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.artifactslab.newspan.retrofit.apiClient
import com.namageoff.actnowaz.features.main.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsViewModel : ViewModel() {
    private var mutableLiveData: MutableLiveData<List<NewsResponse>>? = null

    fun init() {
        when (mutableLiveData == null) {
            true -> mutableLiveData = getNews("", "")
        }
    }

    fun getNewsRepository(): MutableLiveData<List<NewsResponse>>? {
        return mutableLiveData
    }

    private fun getNews(source: String, key: String): MutableLiveData<List<NewsResponse>> {
        val newsData = MutableLiveData<List<NewsResponse>>()

        apiClient().getRepositories().enqueue(object : Callback<List<NewsResponse>> {
            override fun onResponse(call: Call<List<NewsResponse>>, response: Response<List<NewsResponse>>) {
                if (response.isSuccessful) {
                    newsData.value = response.body()
                    Log.w("Resposne", "" + response.body())
                }
            }

            override fun onFailure(call: Call<List<NewsResponse>>, t: Throwable) {
                newsData.value = null
            }
        })
        return newsData
    }

}