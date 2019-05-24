package com.namageoff.actnowaz.application

import android.app.Application
import com.artifactslab.newspan.retrofit.initRetrofit

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initRetrofit(this)
    }
}
