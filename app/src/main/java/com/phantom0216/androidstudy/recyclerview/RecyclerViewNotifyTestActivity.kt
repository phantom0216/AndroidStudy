package com.phantom0216.androidstudy.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.phantom0216.androidstudy.MainActivityRecyclerAdapter
import com.phantom0216.androidstudy.RouterConfig
import com.phantom0216.androidstudy.databinding.ActivityNotifyTestBinding

@Route(path = RouterConfig.RECYCLERVIEWNOTIFY)
class RecyclerViewNotifyTestActivity : AppCompatActivity() {

    private lateinit var mViewBinding: ActivityNotifyTestBinding
    private lateinit var mAdapter: MainActivityRecyclerAdapter
    private val mItems = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityNotifyTestBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        initViews()
        bindData()
    }

    private fun initViews() {
        mViewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = MainActivityRecyclerAdapter()
        mViewBinding.recyclerView.adapter = mAdapter

        mViewBinding.changeBtn.setOnClickListener {
            changeAction()
        }
    }

    private fun bindData() {
        for (index in 0..10) { //改为100时，不会崩，第3条内容变为4
            mItems.add(index.toString())
        }

        mAdapter.onDataSetChanged(mItems)
    }

    private fun changeAction() {
        mAdapter.mItems.removeAt(3)
        mAdapter.notifyItemRangeChanged(3, 1)
    }
}