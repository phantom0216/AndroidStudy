package com.phantom0216.androidstudy.monitor

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Choreographer
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

class SmartFpsMonitor {

    companion object {
        private const val TAG = "SmartFpsMonitor"
    }
    // 活跃期检测相关
    private var isActivePeriod = false
    private var lastActivityTime = 0L
    private var lastRealDrawTime = 0L
    private var lastFrameHasDraw = false
    private val activityTimeout = 1000L // 无操作后超时时间（ms）

    // FPS 统计相关
    private var totalFrames = 0
    private var activeDurationMs = 0L
    private var averageFps = 0

    fun start(activity: Activity?) {
        setupActivityDetection(activity)
        startFpsSampling()
    }

    // 核心方法：获取真实平均 FPS（排除静止状态）
    fun getAverageFps(): Int = averageFps

    // ========== 实现细节 ==========
    private fun setupActivityDetection(activity: Activity?) {
        // 方法1：监听触摸事件（适用于 Activity/View）
//        activity?.window?.decorView?.setOnTouchListener { _, event ->
//            if (event.actionMasked == MotionEvent.ACTION_DOWN && !isActivePeriod) {
//                markActivityStart()
//            }
//            false // 不消费事件
//        }

        // 方法2：监听 View 树绘制（检测内容变化）
        val viewTreeObserver = activity?.window?.decorView?.viewTreeObserver
        viewTreeObserver?.addOnDrawListener {
            lastFrameHasDraw = true
            lastRealDrawTime = System.currentTimeMillis()
            if (!isActivePeriod && (activity as? LifecycleOwner)?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
                markActivityStart() // 非连续绘制视为新活跃期
            }
        }
        viewTreeObserver?.addOnGlobalLayoutListener {
            Log.d(TAG, "globalLayout")
        }
        (activity as? LifecycleOwner)?.lifecycle?.addObserver(object : DefaultLifecycleObserver {
            override fun onPause(owner: LifecycleOwner) {
                super.onPause(owner)
                stopSampling()
            }
        })
    }

    private fun markActivityStart() {
        Log.d(TAG, "markActivityStart")
        isActivePeriod = true
        lastActivityTime = System.currentTimeMillis()
        checkActivityTimeout() // 启动超时检测
    }

    private fun checkActivityTimeout() {
        Handler(Looper.getMainLooper()).postDelayed({
            val inactiveTime = System.currentTimeMillis() - lastActivityTime
            val lastDrawDuration = System.currentTimeMillis() - lastRealDrawTime
            if (inactiveTime >= activityTimeout || lastDrawDuration >= activityTimeout) { // 超过1s没绘制后计算最终平均 FPS
                stopSampling()
            } else {
                checkActivityTimeout() // 继续检测
            }
        }, activityTimeout)
    }

    private fun stopSampling() {
        Log.d(TAG, "stopSampling")
        isActivePeriod = false
        updateAverageFps()
    }

    private fun startFpsSampling() {
        Choreographer.getInstance().postFrameCallback(object : Choreographer.FrameCallback {
            private var lastFrameTime = 0L

            override fun doFrame(frameTimeNanos: Long) {
                val currentTime = System.currentTimeMillis()
                if (isActivePeriod && lastFrameHasDraw) {
                    // 仅活跃期统计
                    totalFrames++
                    activeDurationMs += (currentTime - lastFrameTime).coerceAtLeast(0)
                }
                lastFrameTime = currentTime
                lastFrameHasDraw = false
                Choreographer.getInstance().postFrameCallback(this)
            }
        })
    }

    private fun updateAverageFps() {
        if (activeDurationMs > 0) {
            averageFps = (totalFrames * 1000 / activeDurationMs).toInt()
        }
        totalFrames = 0
        activeDurationMs = 0
        Log.d(TAG, "averageFps: $averageFps")
    }
}