package xyz.geminiwen.hyperdown;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.text.style.ReplacementSpan;

/**
 * Created by geminiwen on 18/10/2016.
 */

public class CodeSpan extends ReplacementSpan {

    private static final float radius = 10;

    private Drawable drawable;
    private float padding;
    private int codeColor;

    public CodeSpan(int color) {
        this(color, Color.parseColor("#c7254e"));
    }

    public CodeSpan(int color, int codeColor) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        this.drawable = d;
        this.codeColor = codeColor;
    }


    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        padding = paint.measureText("t");
        return (int) (paint.measureText(text, start, end) + padding * 2);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        int width = (int) (paint.measureText(text, start, end) + padding * 2);
        drawable.setBounds((int) x, top, (int) x + width, bottom);
        drawable.draw(canvas);

        int color = paint.getColor();
        paint.setColor(codeColor);
        canvas.drawText(text, start, end, x + padding, y, paint);
        paint.setColor(color);
    }
}
