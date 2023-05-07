package com.ortin.gesturetranslator.custom_views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.ortin.gesturetranslator.R;

public class PaintHand extends View {

    private final Context context;

    private Paint drawPaint, canvasPaint, pointPaint;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    public PaintHand(Context context) {
        this(context, null);
    }

    public PaintHand(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintHand(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PaintHand(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init();
    }

    private void init() {
        drawPaint = new Paint();
        pointPaint = new Paint();

        drawPaint.setColor(context.getColor(R.color.black));
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(10);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        pointPaint.setColor(context.getColor(R.color.purple_500));
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeWidth(8);
        pointPaint.setStyle(Paint.Style.STROKE);
        pointPaint.setStrokeJoin(Paint.Join.ROUND);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    public void drawHand(float[] coordinates) {
        canvasBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);

        for (int i = 0; i < coordinates.length; i += 2) {
            drawCanvas.drawCircle(coordinates[i] * getWidth(), coordinates[i + 1] * getHeight(), 5, pointPaint);
        }

        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
    }
}
