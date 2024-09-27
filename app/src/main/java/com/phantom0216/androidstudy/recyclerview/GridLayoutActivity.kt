package com.phantom0216.androidstudy.recyclerview

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.phantom0216.androidstudy.BaseRecyclerAdapter
import com.phantom0216.androidstudy.R
import com.phantom0216.androidstudy.RouterConfig

@Route(path = RouterConfig.RECYCLERVIEWGRID)
class GridLayoutActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_layout)
        initViews()
    }

    private fun initViews() {
        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = GridLayoutManager(this, 5)
        mRecyclerView.adapter = Adapter()
        mRecyclerView.addItemDecoration(CustomItemDecoration())
    }

    class Adapter : BaseRecyclerAdapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.grid_item_layout, parent, false)
            return object : RecyclerView.ViewHolder(view) {

            }
        }

        override fun getItemCount(): Int {
            return 5
        }
    }

    class CustomItemDecoration : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val layoutManager = parent.layoutManager as? GridLayoutManager ?: return
            val gridSize = layoutManager.spanCount
            if (gridSize <= 0) {
                return
            }
            val position = parent.getChildAdapterPosition(view)

            // 获取第几列
            val column = position % gridSize
            val edgeH = dp2px(parent.context, 38)
            val itemWidth = dp2px(parent.context, 45)

            // p为每个Item都需要减去的间距，要是固定p也可反推出spaceH
            val  p = parent.width / gridSize - itemWidth
            val spaceH = (p * gridSize - 2 * edgeH) / (gridSize - 1)
            val left = edgeH + column * (spaceH - p)
            val right = p - left

            Log.d("outRect", "left: $left, right: $right, spaceH:$spaceH")
            outRect.left = left
            outRect.right = right
        }
    }

    companion object {
        @JvmStatic
        fun dp2px(context: Context, dp: Int): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
                context.resources.displayMetrics
            ).toInt()
        }
    }

}