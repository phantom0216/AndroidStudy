package com.phantom0216.androidstudy

import android.app.Application
import android.webkit.WebView
import com.alibaba.android.arouter.launcher.ARouter

class CApplication : Application() {

    companion object {
        lateinit var INSTANCE: CApplication
    }
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        WebView.setWebContentsDebuggingEnabled(true)
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }

}