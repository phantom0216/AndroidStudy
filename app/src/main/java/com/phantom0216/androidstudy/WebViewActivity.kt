package com.phantom0216.androidstudy

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AbsoluteLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route


@Route(path = RouterConfig.WEBVIEW)
class WebViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        val webView = findViewById<View>(R.id.webview) as WebView
        val header = TextView(this)
        val params = AbsoluteLayout.LayoutParams(-1, 300, 0, 500)
        header.text = "1234566"
        header.setBackgroundColor(Color.RED)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }


         webView.addView(header, 0, params);
//        webView.addView(header, params)
        webView.loadUrl("http://www.qq.com")
    }
}