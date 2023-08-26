package com.phantom0216.androidstudy

import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecyclerAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (!holder.itemView.hasOnClickListeners()) {
            holder.itemView.setOnClickListener { view ->
                onItemClickListener?.onItemClick(
                    view,
                    position
                )
            }
        }
    }
}