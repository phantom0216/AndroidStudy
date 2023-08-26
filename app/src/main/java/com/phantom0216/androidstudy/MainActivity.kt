package com.phantom0216.androidstudy

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.phantom0216.androidstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), BaseRecyclerAdapter.OnItemClickListener {

    private lateinit var mViewBinding: ActivityMainBinding
    private lateinit var mAdapter: MainActivityRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        initViews()
        bindData()
    }

    private fun initViews() {
        mViewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = MainActivityRecyclerAdapter().apply {
            setOnItemClickListener(this@MainActivity)
        }
        mViewBinding.recyclerView.adapter = mAdapter
    }

    private fun bindData() {
        val items = mutableListOf<String>()
        items.add("recyclerView")
        items.add("协程")

        mAdapter.onDataSetChanged(items)
    }

    override fun onItemClick(view: View?, position: Int) {
        when (position) {
            0 -> ARouter.getInstance().build(RouterConfig.RECYCLERVIEWNOTIFY).navigation();
        }
    }
}