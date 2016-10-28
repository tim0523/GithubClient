package com.yuyh.github.widget.viewpager.indicator.slidebar;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.TextView;

import com.yuyh.github.widget.viewpager.indicator.Indicator;

/**
 * tab的文本多宽scrollbar就显示多宽，实现新浪个人首页的tab的scrollbar效果
 *
 * @author LuckyJayce
 */
public class TextWidthColorBar extends ColorBar {
    private Indicator indicator;
    private int realWidth = 0;

    public TextWidthColorBar(Context context, Indicator indicator, int color, int height) {
        super(context, color, height);
        this.indicator = indicator;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int width1 = getTextWidth(getTextView(position));
        int width2 = getTextWidth(getTextView(position + 1));
        realWidth = (int) (width1 * (1 - positionOffset) + width2 * (positionOffset));
    }

    @Override
    public int getWidth(int tabWidth) {
        if (realWidth == 0) {
            if (indicator.getAdapter() != null) {
                TextView textView = getTextView(indicator.getCurrentItem());
                if (textView != null) {
                    realWidth = getTextWidth(textView);
                }
            }
        }
        return realWidth;
    }

    /**
     * 如果tab不是textView，可以通过重写该方法，返回tab里面的textView。
     *
     * @param position
     * @return
     */
    protected TextView getTextView(int position) {
        return (TextView) indicator.getItemView(position);
    }

    private int getTextWidth(TextView textView) {
        if (textView == null) {
            return 0;
        }
        Rect bounds = new Rect();
        String text = textView.getText().toString();
        Paint paint = textView.getPaint();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.left + bounds.width();
        return width;
    }

}
