package com.phantom0216.androidstudy.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.phantom0216.androidstudy.CommonUtil
import com.phantom0216.androidstudy.RouterConfig
import com.phantom0216.androidstudy.databinding.ActivityViewpagerClipBinding

//设置margin方式露出左右item在android 8.1上有兼容问题，为了解决此问题，改用padding方式
@Route(path = RouterConfig.VIEWPAGERCLIP)
class ViewPagerClipTestActivity : AppCompatActivity() {

    private lateinit var mViewBinding: ActivityViewpagerClipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityViewpagerClipBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        val dataList = mutableListOf<String>()
        for (i in 1..5) {
            dataList.add(i.toString())
        }

        val adapter = ViewPagerClipAdapter()
        adapter.mItems.addAll(dataList)

        mViewBinding.viewPager.offscreenPageLimit = 1
        mViewBinding.viewPager.adapter = adapter

        //clipToPadding意思是设置改标识的ViewGroup的childView能否绘制到该ViewGroup的padding上,只对当前ViewGroup生效
        val padding = CommonUtil.dp2px(this, 12F)
        val recyclerView = mViewBinding.viewPager.getChildAt(0) as? RecyclerView
        recyclerView?.setPadding(padding, 0, padding, 0)
        recyclerView?.clipToPadding = false
    }
}