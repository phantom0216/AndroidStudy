package com.phantom0216.androidstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.phantom0216.androidstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mViewBinding: ActivityMainBinding
    private lateinit var mAdapter: MainActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        initViews()
        bindData()
    }

    private fun initViews() {
        mViewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = MainActivityAdapter()
        mViewBinding.recyclerView.adapter = mAdapter
    }

    private fun bindData() {
        val items = mutableListOf<String>()
        items.add("recyclerView")
        items.add("协程")

        mAdapter.onDataSetChanged(items)
    }
}