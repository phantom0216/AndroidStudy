package com.phantom0216.androidstudy

import android.webkit.WebView

object WebViewPool {
    private val cacheList = mutableListOf<WebView>()
    fun offer(webView: WebView) {
        cacheList.add(webView)
    }

    fun poll(): WebView {
        return cacheList.removeAt(0)
    }

    fun clear() {
        for (webView in cacheList) {
            webView.destroy()
        }
        cacheList.clear()
    }

    fun preload() {
        offer(WebView(CApplication.INSTANCE))
    }

    fun size(): Int {
        return cacheList.size
    }
}