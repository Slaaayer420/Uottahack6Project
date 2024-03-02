package com.example.uottahack6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class BatteryCircleView extends View {

    private float batteryPercentage = 0f;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG); // For drawing text
    private final RectF rectF = new RectF();

    public BatteryCircleView(Context context) {
        super(context);
        init();
    }

    public BatteryCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BatteryCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20f); // Adjust the stroke width as needed
        paint.setColor(getContext().getResources().getColor(android.R.color.holo_blue_dark)); // Circle color

        textPaint.setColor(getContext().getResources().getColor(android.R.color.black)); // Text color
        textPaint.setTextSize(40f); // Adjust text size as needed
        textPaint.setTextAlign(Paint.Align.CENTER); // Center text
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        float diameter = Math.min(width, height);
        float pad = paint.getStrokeWidth() / 2;
        rectF.set(pad + getPaddingLeft(), pad + getPaddingTop(), diameter - pad + getPaddingLeft(), diameter - pad + getPaddingTop());

        // Draw background circle
        paint.setColor(getContext().getResources().getColor(android.R.color.darker_gray));
        canvas.drawOval(rectF, paint);

        // Draw foreground circle
        paint.setColor(getContext().getResources().getColor(android.R.color.holo_blue_dark));
        canvas.drawArc(rectF, -90, 360 * (batteryPercentage / 100), false, paint);

        // Draw text
        float x = width / 2f + getPaddingLeft();
        float y = (height / 2f + getPaddingTop()) - ((textPaint.descent() + textPaint.ascent()) / 2f);
        canvas.drawText(String.format("%.0f%%", batteryPercentage), x, y, textPaint);
    }

    public void setBatteryPercentage(float percentage) {
        this.batteryPercentage = percentage;
        invalidate(); // Invalidate the view to trigger a redraw
    }
}