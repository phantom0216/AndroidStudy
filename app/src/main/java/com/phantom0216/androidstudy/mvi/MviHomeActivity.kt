package com.phantom0216.androidstudy.mvi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.phantom0216.androidstudy.RouterConfig

@Route(path = RouterConfig.MVI)
class MviHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



}