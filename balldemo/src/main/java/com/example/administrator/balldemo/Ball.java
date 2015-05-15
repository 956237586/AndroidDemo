package com.example.administrator.balldemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HYL on 2015/5/15.
 */
public class Ball extends View {
    private int x;
    private int y;
    private int radius;
    private double dx;
    private double dy;
    private Paint paint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, radius, paint);
    }

    public Ball(Context context) {
        super(context);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
    }

    public Ball(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Ball(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public Ball(Context context, int radius) {
        this(context);
        this.radius = radius;
    }

    public Ball(Context context, int radius, int dx, int dy) {
        this(context, radius);
        this.dx = dx;
        this.dy = dy;
    }

    public Ball(Context context, int radius, int step) {
        this(context, radius);
        this.dx = Math.abs(step) * Math.cos(Math.PI / 4) + 1;
        this.dy = Math.abs(step) * Math.sin(Math.PI / 4) + 1;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public boolean isBorderTop() {
        return y - radius <= 0;
    }

    public boolean isBorderBottom() {
        return y + radius >= getHeight();
    }

    public boolean isBorderLeft() {
        return x - radius <= 0;
    }

    public boolean isBorderRight() {
        return x + radius >= getWidth();
    }

    public void checkBorderVertical() {
        if (isBorderTop())
            y = radius + 1;
        else if (isBorderBottom())
            y = getHeight() - radius - 1;
    }

    public void checkBorderHorizontal() {
        if (isBorderLeft())
            x = radius + 1;
        else if (isBorderRight())
            x = getWidth() - radius - 1;
    }

    public void checkBorder() {
        checkBorderVertical();
        checkBorderHorizontal();
    }

    public void move() {
        checkBorder();
        x += dx;
        y += dy;
        invalidate();
    }

}
