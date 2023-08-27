package com.phantom0216.androidstudy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phantom0216.androidstudy.databinding.MainListItemLayoutBinding

class MainActivityRecyclerAdapter : BaseRecyclerAdapter<MainActivityRecyclerAdapter.ItemViewHolder>() {

    val mItems = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewBinding =
            MainListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bindData(mItems[position])
    }

    fun onDataSetChanged(items: List<String>) {
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    class ItemViewHolder(private val viewBinding: MainListItemLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bindData(title: String) {
            viewBinding.root.text = title
        }
    }
}