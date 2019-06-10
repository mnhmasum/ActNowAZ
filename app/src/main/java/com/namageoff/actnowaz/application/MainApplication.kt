package com.namageoff.actnowaz.application

import android.app.Application
import com.artifactslab.newspan.retrofit.initRetrofit

class MainApplication : Application(), LifeCycleDelegate {

    companion object {
        var isAppOnForeground = false
    }

    override fun onAppBackgrounded() {
        isAppOnForeground = false
        //Toast.makeText(applicationContext, "Background", Toast.LENGTH_SHORT).show()
    }

    override fun onAppForegrounded() {
        isAppOnForeground = true
        //Toast.makeText(applicationContext, "Foreground", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate() {
        super.onCreate()
        initRetrofit(this)
        val lifeCycleHandler = AppLifecycleHandler(this)
        registerLifecycleHandler(lifeCycleHandler)
    }


    private fun registerLifecycleHandler(lifeCycleHandler: AppLifecycleHandler) {
        registerActivityLifecycleCallbacks(lifeCycleHandler)
        registerComponentCallbacks(lifeCycleHandler)
    }

}


