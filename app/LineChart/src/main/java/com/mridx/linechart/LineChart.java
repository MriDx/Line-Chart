package com.mridx.linechart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class LineChart extends View {

    private static final int DEFAULT_lINE_COLOR = Color.parseColor("#9C27B0");
    private static final int DEFAULT_CURSOR_COLOR = Color.parseColor("#D56C75");
    private static final int DEFAULT_CURSOR_WIDTH = 3;
    private static final int DEFAULT_lINE_WIDTH = 5;
    private static final int DEFAULT_CIRCLE_RADIUS = 10;

    private int lineColor;
    private int cursorColor;
    private int lineWidth;
    private int cursorWidth;
    private int circleRadius;
    private boolean showRuler = true;

    private Paint paint = new Paint();
    private float[] pointsdata = new float[]{5, 2, 3, 5, 6, 8};


    public LineChart(Context context) {
        super(context);
        render(context, null, -1);
    }

    public LineChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        render(context, attrs, -1);
    }

    public LineChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        render(context, attrs, defStyleAttr);
    }


    private void render(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LineChart);
        try {

            lineColor = typedArray.getColor(R.styleable.LineChart_lineColor, DEFAULT_lINE_COLOR);
            cursorColor = typedArray.getColor(R.styleable.LineChart_cursorWidth, DEFAULT_CURSOR_COLOR);
            lineWidth = typedArray.getDimensionPixelSize(R.styleable.LineChart_lineWidth, DEFAULT_lINE_WIDTH);
            cursorWidth = typedArray.getDimensionPixelSize(R.styleable.LineChart_cursorWidth, DEFAULT_CURSOR_WIDTH);
            circleRadius = typedArray.getDimensionPixelSize(R.styleable.LineChart_circleRadius, DEFAULT_CIRCLE_RADIUS);
            showRuler = typedArray.getBoolean(R.styleable.LineChart_showRuler, true);
        } finally {
            typedArray.recycle();
        }

    }

    public void setDataChart(float[] pointsdata) {
        this.pointsdata = pointsdata.clone();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //if (showRuler) renderCursor(canvas);
        renderChart(canvas);
    }

    private void renderCursor(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(cursorColor);
        paint.setStrokeWidth(cursorWidth);
        float delta = getHeight() / 20;
        canvas.drawLine(delta / 2, (getHeight() - delta), (getWidth() - delta / 2), (getHeight() - delta), paint);
        canvas.drawLine(delta, delta / 2, delta, (getHeight() - delta / 2), paint);
    }

    private void renderChart(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getX(0), getY(pointsdata[0]));

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineWidth);
        paint.setColor(lineColor);

        for (int i = 0; i < pointsdata.length; i++) {
            canvas.drawCircle(getX(i), getY(pointsdata[i]), circleRadius, paint);
            path.lineTo(getX(i), getY(pointsdata[i]));
        }
        canvas.drawPath(path, paint);
    }

    private float getMax(float[] array) {
        float max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private float getY(float value) {
        float height = getHeight() - getPaddingTop() - getPaddingBottom();
        float maxValue = getMax(pointsdata);
        value = (value / maxValue) * height;
        value = height - value;
        value += getPaddingTop();
        return value;
    }

    private float getX(float value) {
        float width = getWidth() - getPaddingLeft() - getPaddingRight();
        float maxValue = pointsdata.length - 1;
        value = (value / maxValue) * width;
        value += getPaddingLeft();
        return value;
    }

}
