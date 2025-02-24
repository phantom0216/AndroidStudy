package com.phantom0216.androidstudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.phantom0216.androidstudy.R;

public class NormalPagerIndicator extends LinearLayout {
   private static final String TAG = "NormalPagerIndicator";

   protected int mCurrentIndex = 0;
   private int mCount = 0;
   protected View mCurrentSelectedView = null;

   protected int normalColor = Color.parseColor("#80FFFFFF");
   protected int selectColor = Color.WHITE;

   public NormalPagerIndicator(Context context) {
      this(context, null);
   }

   public NormalPagerIndicator(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public NormalPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      init(context, attrs);
   }

   protected void init(Context context, AttributeSet attrs) {
      TypedArray typedArray = null;
      try {
         typedArray = context.obtainStyledAttributes(attrs, R.styleable.NormalPagerIndicator);
         normalColor = typedArray.getColor(R.styleable.NormalPagerIndicator_normalColor, normalColor);
         selectColor = typedArray.getColor(R.styleable.NormalPagerIndicator_selectColor, selectColor);
      } catch (Exception e) {
      } finally {
         if (typedArray != null) {
            typedArray.recycle();
         }
      }
   }

   public void refreshUI(int curIdx) {
      if (this.getChildCount() < mCount) {
         int addCount = mCount - this.getChildCount();
         for (int i = 0; i < addCount; i++) {
            View view = getSingleView(i);
            if (view != null) {
               this.addView(view);
            }
         }
      } else {
         if (this.getChildCount() > mCount) {
            int removeCount = this.getChildCount() - mCount;
            for (int i = 0; i < removeCount; i++) {
               if (this.getChildCount() > 0) {
                  this.removeViewAt(0);
               }
            }
         }
      }
      setCurrent(curIdx);
   }

   protected View getSingleView(int index) {
      DotView dot = new DotView(getContext(), normalColor, selectColor);
      LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
      params.weight = 1;
      if (index != 0) {
         params.leftMargin = 10;
      }
      dot.setLayoutParams(params);
      return dot;
   }

   public void setCount(int count) {
      if (count > 0) {
         mCount = count;
         refreshUI(0);
      }
   }

   public void setCount(int count, int curIdx) {
      if (count > 0) {
         mCount = count;
         refreshUI(curIdx);
      }
   }

   public void setCurrent(int current) {
      if (current >= 0 && current < this.getChildCount()) {
         View view = this.getChildAt(current);
         if (mCurrentSelectedView != null) {
            mCurrentSelectedView.setSelected(false);
         }
         mCurrentSelectedView = view;
         view.setSelected(true);
         mCurrentIndex = current;
      }
   }

   public int getCurrentIndex() {
      return this.mCurrentIndex;
   }
}