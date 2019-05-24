package com.artifactslab.newspan.retrofit

import android.app.Application
import com.namageoff.actnowaz.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://raw.githubusercontent.com/carensiehl/ActNowAZ_JSON/master/" //?country=in&apiKey=51020d256c68430ba9bd415505885b3e/

private val logInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private var retrofitOption: Retrofit? = null

private fun provideHttpCache(application: Application): Cache {
    val cacheSize = 10 * 1024 * 1024
    return Cache(application.cacheDir, cacheSize.toLong())
}

private fun httpClientBuilder(): OkHttpClient.Builder =
    OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            this.addInterceptor(logInterceptor)
        }
    }

private fun getRetrofitBuilderDefaults() =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())

private fun provideOkHttpClientOAuth(cache: Cache): OkHttpClient = httpClientBuilder().cache(cache).build()

private fun createRetrofit(application: Application): Retrofit =
    getRetrofitBuilderDefaults().client(provideOkHttpClientOAuth(provideHttpCache(application))).build()

fun initRetrofit(application: Application) {
    if (retrofitOption == null) {
        retrofitOption = createRetrofit(application)
    }
}

fun retrofit(): Retrofit = retrofitOption ?: throw Exception("You need to initialize retrofit before using it!")
