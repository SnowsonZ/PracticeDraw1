package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.bean.LabelLineModule;
import com.hencoder.hencoderpracticedraw1.bean.VersionModel;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {

    private Paint paint;
    private List<VersionModel> mData;
    //view默认宽高
    private int mWidth, mHeight;
    private float mRadius;
    private static final String TITLE_TAG = "饼图";
    private RectF rect;
    //最大份额所在的RectF
    private RectF maxRect;
    //最大份额的圆心偏移量
    private int detalMax = 20;
    private float startAngel;
    private float total;
    private float max;
    private boolean isMax;

    public Practice11PieChartView(Context context) {
        super(context);
        prepare();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        prepare();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        prepare();
    }

    private void prepare() {
        mData = new ArrayList<>();
        mData.add(new VersionModel("Froyo", 2, Color.parseColor("#555A5C")));
        mData.add(new VersionModel("Gingerbread", 20, Color.parseColor("#9C27B0")));
        mData.add(new VersionModel("Ice Cream Sandwich", 20, Color.parseColor("#9E9E9E")));
        mData.add(new VersionModel("Jelly Bean", 240, Color.parseColor("#009688")));
        mData.add(new VersionModel("KitKat", 400, Color.parseColor("#2196F3")));
        mData.add(new VersionModel("Lollipop", 450, Color.parseColor("#F44336")));
        mData.add(new VersionModel("Marshmallow", 200, Color.parseColor("#FFC107")));
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        startAngel = 0;
        for (int i = 0; i < mData.size(); i++) {
            float cur = mData.get(i).getVersionCount();
            max = Math.max(max, mData.get(i).getVersionCount());
            total += cur;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mRadius = mHeight * 0.35f;
        rect = new RectF(-mRadius, -mRadius, mRadius, mRadius);
        maxRect = new RectF(rect.left - detalMax , rect.top - detalMax, rect.right - detalMax,
                rect.bottom - detalMax);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        startAngel = 0;

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        //draw title
        paint.setTextSize(42);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
        canvas.drawText(TITLE_TAG, canvas.getWidth() / 2 - mWidth * 0.1f, canvas.getHeight() * 0.95f, paint);

        //绘制饼图
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(28);
        canvas.translate(mWidth / 2 - mWidth * 0.1f, mHeight / 2);
        for (int i = 0; i < mData.size(); i++) {
            float sweepAngel = mData.get(i).getVersionCount() / total * 360;
            paint.setColor(mData.get(i).getColor());
            RectF destRect = null;
            if(max == mData.get(i).getVersionCount()) {
                destRect = maxRect;
                isMax = true;
            }else {
                destRect = rect;
                isMax = false;
            }
            canvas.drawArc(destRect, startAngel, sweepAngel, true, paint);
            LabelLineModule llm = getLabelLine(sweepAngel, isMax);
            drawLabelLineAndLabel(llm, canvas, mData.get(i).getVersionName());
            startAngel += sweepAngel;
        }

    }

    /**
     * 绘制label指示线及label
     * @param llm　待绘制的线的起点坐标及线的类型
     * @param canvas
     * @param versionName
     */
    private void drawLabelLineAndLabel(LabelLineModule llm, Canvas canvas, String versionName) {
        int destX = 0, destY = 0, lineLength =
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        30, getContext().getResources().getDisplayMetrics()),
                destDetal = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        8, getContext().getResources().getDisplayMetrics()),
                spaceLabelLine = 0;
        if (llm.getLineType() == LabelLineModule.LineType.CENTERRIGHT) {
            destX = llm.getPoint().x;
            destY = llm.getPoint().y;
            lineLength = lineLength - 20;
            spaceLabelLine = 10;
        } else if (llm.getLineType() == LabelLineModule.LineType.BOTTOMRIGHT) {
            destX = llm.getPoint().x + destDetal;
            destY = llm.getPoint().y + destDetal;
            spaceLabelLine = 10;
        } else if (llm.getLineType() == LabelLineModule.LineType.BOTTOMLEFT) {
            destX = llm.getPoint().x - destDetal;
            destY = llm.getPoint().y + destDetal;
            lineLength = -lineLength;
            spaceLabelLine = -(int)(10 + paint.measureText(versionName));
        } else if (llm.getLineType() == LabelLineModule.LineType.CENTERLEFT) {
            destX = llm.getPoint().x;
            destY = llm.getPoint().y;
            lineLength = -lineLength;
            spaceLabelLine = -(int)(10 + paint.measureText(versionName));
        } else if (llm.getLineType() == LabelLineModule.LineType.TOPLEFT) {
            destX = llm.getPoint().x - destDetal;
            destY = llm.getPoint().y - destDetal;
            lineLength = -lineLength;
            spaceLabelLine = -(int)(10 + paint.measureText(versionName));
        } else {
            destX = llm.getPoint().x + destDetal;
            destY = llm.getPoint().y - destDetal;
            spaceLabelLine = 10;
        }

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        paint.setTextSize(24);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawLines(new float[]{llm.getPoint().x, llm.getPoint().y, destX, destY, destX, destY,
                destX + lineLength, destY}, paint);
        canvas.drawText(versionName, destX + lineLength + spaceLabelLine, destY + 10, paint);
    }

    /**
     * 根据三角函数获取每一块扇形的终点坐标，作为label线的起点
     * @param angel
     * @param isMax
     * @return
     */
    private LabelLineModule getLabelLine(float angel, boolean isMax) {
        angel = angel / 2 + startAngel;
        LabelLineModule llm = new LabelLineModule();
        if (angel >= 350 || angel < 2) {
            llm.setLineType(LabelLineModule.LineType.CENTERRIGHT);
        } else if (angel >= 2 && angel < 90) {
            llm.setLineType(LabelLineModule.LineType.BOTTOMRIGHT);
        } else if (angel >= 90 && angel < 170) {
            llm.setLineType(LabelLineModule.LineType.BOTTOMLEFT);
        } else if (angel >= 170 && angel < 190) {
            llm.setLineType(LabelLineModule.LineType.CENTERLEFT);
        } else if (angel >= 190 && angel < 270) {
            llm.setLineType(LabelLineModule.LineType.TOPLEFT);
        } else {
            llm.setLineType(LabelLineModule.LineType.TOPRIGHT);
        }
        Point point = new Point();
        if(isMax) {
            point.set((int) (Math.cos(angel * Math.PI / 180) * mRadius - detalMax), (int) (Math.sin(angel * Math.PI / 180) * mRadius  - detalMax));
        }else {
            point.set((int) (Math.cos(angel * Math.PI / 180) * mRadius), (int) (Math.sin(angel * Math.PI / 180) * mRadius));
        }
        llm.setPoint(point);
        return llm;
    }

}
