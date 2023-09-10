package com.phantom0216.androidstudy.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phantom0216.androidstudy.BaseRecyclerAdapter
import com.phantom0216.androidstudy.databinding.ViewpagerClipItemBinding

class ViewPagerClipAdapter : BaseRecyclerAdapter<ViewPagerClipAdapter.ItemViewHolder>() {

    val mItems = mutableListOf<String>()

    class ItemViewHolder(private val viewBinding: ViewpagerClipItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bindData(title: String) {
            viewBinding.tv.text = title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewBinding =
            ViewpagerClipItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bindData(mItems[position])
    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}