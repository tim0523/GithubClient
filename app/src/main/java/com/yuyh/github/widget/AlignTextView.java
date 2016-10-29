package com.yuyh.github.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyh.
 * @date 16/4/10.
 */
public class AlignTextView extends TextView {
    private float textHeight;
    private int width;
    private List<String> lines = new ArrayList<String>();
    private List<Integer> tailLines = new ArrayList<Integer>();
    private Align align = Align.ALIGN_LEFT;

    public enum Align {
        ALIGN_LEFT,
        ALIGN_CENTER,
        ALIGN_RIGHT,
    }

    public AlignTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        paint.drawableState = getDrawableState();
        width = getMeasuredWidth();
        String text = getText().toString();

        Paint.FontMetrics fm = paint.getFontMetrics();
        Layout layout = getLayout();

        // layout.getLayout(): 4.4.3 NullPointerException
        if (layout == null) {
            return;
        }

        textHeight = fm.descent - fm.ascent;
        textHeight = textHeight * layout.getSpacingMultiplier() + layout.getSpacingAdd();

        float firstHeight = getTextSize();

        int gravity = getGravity();
        if ((gravity & 0x1000) == 0) {
            firstHeight = firstHeight + (textHeight - firstHeight) / 2;
        }

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        width = width - paddingLeft - paddingRight;

        lines.clear();
        tailLines.clear();

        String[] items = text.split("\\n");
        for (String item : items) {
            calc(paint, item);
        }

        for (int i = 0; i < lines.size(); i++) {
            float drawY = i * textHeight + firstHeight;
            String line = lines.get(i);
            float drawSpacingX = paddingLeft;
            float gap = (width - paint.measureText(line));
            float interval = gap / (line.length() - 1);

            if (tailLines.contains(i)) {
                interval = 0;
                if (align == Align.ALIGN_CENTER)
                    drawSpacingX += gap / 2;
                else if (align == Align.ALIGN_RIGHT)
                    drawSpacingX += gap;
            }

            for (int j = 0; j < line.length(); j++) {
                float drawX = paint.measureText(line.substring(0, j))
                        + interval * j;
                canvas.drawText(line.substring(j, j + 1), drawX + drawSpacingX,
                        drawY, paint);
            }
        }
    }

    public void setAlign(Align align) {
        this.align = align;
        invalidate();
    }

    private void calc(Paint paint, String text) {
        if (text.length() == 0) {
            lines.add("\n");
            return;
        }
        StringBuffer sb = new StringBuffer("");
        int startPosition = 0; // 起始位置
        for (int i = 0; i < text.length(); i++) {
            if (paint.measureText(text.substring(startPosition, i + 1)) > width) {
                startPosition = i;
                lines.add(sb.toString());
                sb = new StringBuffer();
            }
            sb.append(text.charAt(i));
        }
        if (sb.length() > 0)
            lines.add(sb.toString());

        tailLines.add(lines.size() - 1);
    }
}