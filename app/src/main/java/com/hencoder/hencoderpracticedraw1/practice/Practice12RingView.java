package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: snowson
 * created on: 17-12-10 上午5:25
 * description:
 */

public class Practice12RingView extends View {

    private Paint mPaint;
    private RectF arcRectf;
    private int mRadius = 250;

    public Practice12RingView(Context context) {
        super(context);
        prepare();
    }

    public Practice12RingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        prepare();
    }

    public Practice12RingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        prepare();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Practice12RingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        prepare();
    }

    private void prepare() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(40);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        arcRectf = new RectF(width / 2 - mRadius, height / 2 - mRadius,
                width / 2 + mRadius, height / 2 + mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2,
                250, mPaint);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.RED);
        canvas.drawArc(arcRectf, 0, 100, false, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(arcRectf, 150, 50, false, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawArc(arcRectf, 200, 70, false, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(arcRectf, 270, 30, false, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawArc(arcRectf, 300, 60, false, mPaint);
    }
}
