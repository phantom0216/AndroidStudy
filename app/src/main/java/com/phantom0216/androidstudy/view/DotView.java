package com.phantom0216.androidstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;

import com.phantom0216.androidstudy.CommonUtil;

public class DotView extends View {
   protected boolean isSelected = false;
   protected Paint paint;

   protected int normalColor = Color.parseColor("#FFFFFFFF");
   protected int selectColor = Color.WHITE;

   public DotView(Context context) {
      super(context);
      init();
   }

   public DotView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      init();
   }

   public DotView(Context context, AttributeSet attrs) {
      super(context, attrs);
      init();
   }

   public DotView(Context context, @ColorInt int normalColor, @ColorInt int selectColor) {
      super(context);
      this.normalColor = normalColor;
      this.selectColor = selectColor;
      init();
   }

   private void init() {
      // setLayerType(View.LAYER_TYPE_SOFTWARE, null);
      paint = new Paint();
      paint.setAntiAlias(true);
      paint.setStyle(Paint.Style.FILL);
      paint.setColor(normalColor);
      //阴影方案1：ViewOutlineProvider+android:elevation
      // setOutlineProvider(new ViewOutlineProvider() {
      //    @Override
      //    public void getOutline(View view, Outline outline) {
      //       outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), view.getHeight() / 2);
      //    }
      // });
   }

   @Override
   public void setSelected(boolean selected) {
      super.setSelected(selected);
      isSelected = selected;
      invalidate();
   }

   @Override
   protected void onDraw(Canvas canvas) {
      int shadowWidth = CommonUtil.dp2px(getContext(), 2F);
      int width = getWidth();
      int height = getHeight();
      int radius = height / 2;
      paint.clearShadowLayer();
      paint.setColor(Color.TRANSPARENT);
      canvas.drawPaint(paint);
      paint.setColor(isSelected ? selectColor : normalColor);
      //阴影方案2：setShadowLayer
      paint.setShadowLayer(shadowWidth, 0F, 0F, Color.parseColor(
          "#66000000"));
      //阴影方案3：BlurMaskFilter，缺点无法设置阴影颜色
      // paint.setMaskFilter(new BlurMaskFilter(12, BlurMaskFilter.Blur.SOLID));
      canvas.drawCircle(width / 2, height / 2, radius, paint);
      // paint.setColor(Color.RED);
      // paint.setStyle(Paint.Style.STROKE);
      // canvas.drawRect(0, 0, width, height, paint);
   }
}
