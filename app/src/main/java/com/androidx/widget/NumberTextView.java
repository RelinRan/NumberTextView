package com.androidx.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * 圆点数字 + 文字
 */
public class NumberTextView extends AppCompatTextView {

    private Paint paint;
    //圆点文字
    private String text = "0";
    //圆点文字大小
    private int textSize = 16;
    //圆点文字颜色
    private int textColor = Color.WHITE;
    //圆点颜色
    private int solidColor = Color.RED;
    //圆点半径大小
    private int radius = 18;
    //圆点文字上间距
    private int textMarginTop = 10;
    //圆点文字下间距
    private int textMarginRight = 10;

    public NumberTextView(Context context) {
        super(context);
        initAttributeSet(context, null);
    }

    public NumberTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(context, attrs);
    }

    public NumberTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributeSet(context, attrs);
    }

    private void initAttributeSet(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NumberTextView);
            text = array.getString(R.styleable.NumberTextView_numberText);
            text = TextUtils.isEmpty(text) ? "0" : text;
            textSize = array.getDimensionPixelSize(R.styleable.NumberTextView_numberTextSize, textSize);
            textColor = array.getColor(R.styleable.NumberTextView_numberTextColor, textColor);
            solidColor = array.getColor(R.styleable.NumberTextView_numberSolidColor, solidColor);
            textMarginTop = array.getDimensionPixelSize(R.styleable.NumberTextView_numberMarginTop, textMarginTop);
            textMarginRight = array.getDimensionPixelSize(R.styleable.NumberTextView_numberMarginRight, textMarginRight);
            array.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (text.equals("0")) {
            return;
        }
        paint = new Paint();
        paint.setAntiAlias(true);
        //圆点背景
        paint.setColor(solidColor);
        int textSpace = 0;
        if (text.length() > 2) {
            textSpace = (text.length() - 2) * textSize / 2;
        }
        int cx = getMeasuredWidth() - radius - textSpace - textMarginRight;
        int cy = radius + textMarginTop;
        paint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(cx - radius - textSpace, cy - radius, cx + radius + textSpace, cy + radius);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        //文字
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text, cx - rect.width() / 2, cy + rect.height() / 2, paint);
    }

    /**
     * 设置文字
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    /**
     * 设置文字大小
     *
     * @param textSize
     */
    public void setTextSize(int textSize) {
        this.textSize = textSize;
        invalidate();
    }

    /**
     * 设置颜色
     *
     * @param solidColor
     */
    public void setSolidColor(int solidColor) {
        this.solidColor = solidColor;
        invalidate();
    }

    /**
     * 设置圆点半径
     *
     * @param radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

}

