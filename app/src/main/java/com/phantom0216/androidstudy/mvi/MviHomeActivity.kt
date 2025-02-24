package com.phantom0216.androidstudy.mvi

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.phantom0216.androidstudy.R
import com.phantom0216.androidstudy.RouterConfig
import com.phantom0216.androidstudy.view.LivePendantsIndicator
import kotlinx.coroutines.delay

@Route(path = RouterConfig.MVI)
class MviHomeActivity : AppCompatActivity() {

    private lateinit var mTextView: TextView
    private lateinit var mImageView: ImageView
    private lateinit var mProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvi)
        mTextView = findViewById(R.id.tv)
        mImageView = findViewById(R.id.ad)
        val displayMetrics = resources.displayMetrics
        val text = "widthPixels: " + displayMetrics.widthPixels
        mTextView.text = text
        mProgressBar = findViewById(R.id.progress_bar)
        lifecycleScope.launchWhenResumed {
            val startTime = System.currentTimeMillis()
            val tick = 1000 / 60
            repeat(10000 / tick) {
                val progress = ((System.currentTimeMillis() - startTime) / 10000F) * 100
//                Log.d("MviHomeActivity", progress.toString())
                mProgressBar.progress = progress.toInt()
                delay(tick.toLong())
            }
        }
        val drawable = resources.getDrawable(R.drawable.ad_icon)
        drawable.setBounds(-40, -40, 0, 0)
        mTextView.setCompoundDrawables(drawable, null, null, null)

        val indicator = findViewById<LivePendantsIndicator>(R.id.indicator)
        indicator.setCount(3, 0)

        lifecycleScope.launchWhenResumed {
            repeat(10) {
                indicator.setCurrent(it % 3)
                delay(2000L)
            }
        }

        val start1 = System.currentTimeMillis()
        val webView1 = WebView(this)
        Log.d("MviHomeActivity", "first init cost:" + (System.currentTimeMillis() - start1))
        val start2 = System.currentTimeMillis()
        val webView2 = WebView(this)
        Log.d("MviHomeActivity", "second init cost:" + (System.currentTimeMillis() - start2))

        mImageView.setOnClickListener {
            MDDialogFragment().show(supportFragmentManager, "MDDialogFragment")
        }
    }


}