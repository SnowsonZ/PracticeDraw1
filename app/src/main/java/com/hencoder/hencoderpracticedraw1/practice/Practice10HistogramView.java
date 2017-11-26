package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.bean.VersionModel;

import java.util.ArrayList;
import java.util.List;

public class Practice10HistogramView extends View {

    private Paint paint;
    private List<VersionModel> mData;
    //每组宽度
    private float perWidth;
    //组间距
    private float space;
    //绘制内容的宽
    private float mWidth;
    //绘制内容的高
    private float mHeight;
    //每组的起始位置
    private float startX;
    private static final String TITLE_TAG = "直方图";

    public Practice10HistogramView(Context context) {
        super(context);
        prepare();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        prepare();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        prepare();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void prepare() {
        mData = new ArrayList<>();
        mData.add(new VersionModel("Froyo", 2, Color.parseColor("#72B916")));
        mData.add(new VersionModel("GB", 20, Color.parseColor("#72B916")));
        mData.add(new VersionModel("ICS",20, Color.parseColor("#72B916")));
        mData.add(new VersionModel("JB",  240, Color.parseColor("#72B916")));
        mData.add(new VersionModel("KitKat", 400, Color.parseColor("#72B916")));
        mData.add(new VersionModel("L", 450, Color.parseColor("#72B916")));
        mData.add(new VersionModel("M", 200, Color.parseColor("#72B916")));
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(42);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        //draw title
        canvas.drawText(TITLE_TAG, canvas.getWidth() / 2, canvas.getHeight() * 0.9f, paint);
        //draw坐标轴
        paint.setStrokeWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1,
                getContext().getResources().getDisplayMetrics()));
        mWidth = canvas.getWidth() * 0.8f;
        mHeight = canvas.getHeight() * 0.6f;
        canvas.translate(canvas.getWidth() * 0.1f, canvas.getHeight() * 0.7f);
        canvas.drawLine(0, 0, 0, -mHeight, paint);
        canvas.drawLine(0, 0, mWidth, 0, paint);

        //绘制直方图
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);
        space = (mWidth - 40) / mData.size() * 0.2f;
        perWidth = (mWidth - 40) / mData.size() * 0.8f;
        startX = 0;
        for(int i = 0 ; i < mData.size(); i++) {
            paint.setColor(mData.get(i).getColor());
            canvas.drawRect(startX + space, -mData.get(i).getVersionCount(), startX + space + perWidth,
                    0, paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(28);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(mData.get(i).getVersionName(), startX + space + perWidth / 2, 30, paint);
            startX += space + perWidth;
        }

    }
}
