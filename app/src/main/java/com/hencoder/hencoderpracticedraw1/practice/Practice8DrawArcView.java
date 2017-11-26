package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    private Paint paint;
    private RectF rect;

    public Practice8DrawArcView(Context context) {
        super(context);
        prepare();
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        prepare();
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        prepare();
    }

    private void prepare() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        rect = new RectF(400, 300, 700, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
//        canvas.drawArc(rect, -20, -100, true, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rect, 15, 150, false, paint);
        canvas.drawArc(rect, 260, 100, true, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rect, 185, 65, false, paint);
    }
}
