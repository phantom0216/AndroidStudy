package com.phantom0216.androidstudy

import android.content.Context

class CommonUtil {
    companion object {
        @JvmStatic
        fun dp2px(context: Context, dpValue: Float): Int {
            val scale: Float = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }
    }
}