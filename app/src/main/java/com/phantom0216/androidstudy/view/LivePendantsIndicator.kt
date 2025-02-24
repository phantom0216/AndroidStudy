package com.phantom0216.androidstudy.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.phantom0216.androidstudy.CommonUtil

class LivePendantsIndicator@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
)  : NormalPagerIndicator(context, attrs) {

    private val dotLeftMargin = CommonUtil.dp2px(context, 4F)
    override fun getSingleView(index: Int): View {
        val dot = DotView(context, normalColor, selectColor)
        val params = LayoutParams(dotLeftMargin, dotLeftMargin)
        if (index != 0) {
            params.leftMargin = CommonUtil.dp2px(context, 4F)
        }
        dot.layoutParams = params
        return dot
    }
}