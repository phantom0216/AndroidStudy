package com.phantom0216.androidstudy

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class CApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }

}