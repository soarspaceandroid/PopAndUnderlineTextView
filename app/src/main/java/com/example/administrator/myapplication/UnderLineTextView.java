package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/20.
 */

public class UnderLineTextView extends TextView {

    private final static int LINE_EXTRA = 2;

    public UnderLineTextView(Context context) {
        super(context);
    }

    public UnderLineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnderLineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(getText().toString(), 0, getBaseline(), getPaint());
        Paint.FontMetricsInt fm = getPaint().getFontMetricsInt();
        float offsetBottom = getBaseline() + fm.bottom-3; // 3距离底部距离 , 可变更文字与线距离
        getPaint().setStrokeWidth(2);
        canvas.drawLine(0, offsetBottom, getWidth(), offsetBottom, getPaint());

    }
}
