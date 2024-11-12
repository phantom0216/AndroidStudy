package com.phantom0216.androidstudy.mvi

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.phantom0216.androidstudy.R
import com.phantom0216.androidstudy.RouterConfig
import kotlinx.coroutines.delay

@Route(path = RouterConfig.MVI)
class MviHomeActivity : AppCompatActivity() {

    private lateinit var mTextView: TextView
    private lateinit var mProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvi)
        mTextView = findViewById(R.id.tv)
        val displayMetrics = resources.displayMetrics
        val text = "widthPixels: " + displayMetrics.widthPixels
        mTextView.text = text
        mProgressBar = findViewById(R.id.progress_bar)
        lifecycleScope.launchWhenResumed {
            val startTime = System.currentTimeMillis()
            val tick = 1000 / 60
            repeat(10000 / tick) {
                val progress = ((System.currentTimeMillis() - startTime) / 10000F) * 100
                Log.d("MviHomeActivity", progress.toString())
                mProgressBar.progress = progress.toInt()
                delay(tick.toLong())
            }
        }
        val drawable = resources.getDrawable(R.drawable.ad_icon)
        drawable.setBounds(-40, -40, 0, 0)
        mTextView.setCompoundDrawables(drawable, null, null, null)
    }


}