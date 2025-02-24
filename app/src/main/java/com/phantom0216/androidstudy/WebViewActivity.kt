package com.phantom0216.androidstudy

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route


@Route(path = RouterConfig.WEBVIEW)
class WebViewActivity: AppCompatActivity() {

    private lateinit var mRootView: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WebView.setWebContentsDebuggingEnabled(true)
        setContentView(R.layout.activity_webview)
        mRootView = findViewById(R.id.root_view)
        val tv = findViewById<TextView>(R.id.btn)
        tv.setOnClickListener {
            val mWebView = WebView(this)
            mWebView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            mWebView.settings.allowUniversalAccessFromFileURLs = true
            mWebView.settings.javaScriptEnabled = true
            mWebView.settings.domStorageEnabled = true
            mWebView.settings.databaseEnabled = true
            mWebView.settings.blockNetworkImage = false
            mWebView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {
                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun shouldInterceptRequest(
                    view: WebView?,
                    url: String?
                ): WebResourceResponse? {
                    return super.shouldInterceptRequest(view, url)
                }
            }
            WebViewPool.offer(mWebView)
        }

        val clearBtn = findViewById<TextView>(R.id.clearBtn)
        clearBtn.setOnClickListener {
            WebViewPool.clear()
        }

        val preloadBtn = findViewById<TextView>(R.id.preloadBtn)
        preloadBtn.setOnClickListener {
            WebViewPool.preload()
        }

        val attachBtn = findViewById<TextView>(R.id.attachBtn)
        attachBtn.setOnClickListener {
            val webView = WebViewPool.poll()
            mRootView.addView(webView, 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}