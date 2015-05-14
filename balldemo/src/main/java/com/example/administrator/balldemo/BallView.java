package com.example.administrator.balldemo;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by HYL on 2015/5/14.
 */

// 自定义 view,需要实现 一个显式的构造函数，重写 onDraw 方法，一切的操作都在该方法上进行
public class BallView extends View {
    private int x;
    private int y;
    private double dx;
    private double dy;
    private int radius;
    Paint paint = new Paint();

    public BallView(Context context) {
        super(context);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

    }

    public BallView(Context context, int radius) {
        this(context);
        this.radius = radius;
    }

    public BallView(Context context, int radius, double step) {
        this(context, radius);
        this.dx = step * Math.cos(Math.PI / 4);
        this.dy = step * Math.sin(Math.PI / 4);
    }

    public BallView(Context context, int radius, double dx, double dy) {
        this(context, radius);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
        super.onDraw(canvas);
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

    public void setLocation(int x, int y) {
        this.x = x - radius;
        this.y = y - radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void move(boolean a) {
        //System.out.println("width = " + getWidth());
        // System.out.println("height = " + getHeight());
        if (a) checkBorder1();
        else checkBorder2();
        x += dx;
        y += dy;
        // System.out.println("x = " + x);
        // System.out.println("y = " + y);
    }


    public boolean isOnBorderLeft() {
        return x - radius <= 0;
    }

    public boolean isOnBorderRight() {
        return x + radius >= getWidth();
    }

    public boolean isOnBorderTop() {
        return y - radius <= 0;
    }

    public boolean isOnBorderBottom() {
        return y + radius >= getHeight();
    }

    public boolean isOnBorder() {
        return isOnBorderLeft() || isOnBorderRight() || isOnBorderTop() || isOnBorderBottom();
    }

    public BallView checkBorder1() {
        if (isOnBorderLeft() || isOnBorderRight())
            dx = -dx;
        if (isOnBorderTop() || isOnBorderBottom())
            dy = -dy;
        return this;
    }

    public BallView checkBorder2() {
        if (isOnBorderLeft()) {
            x = radius - 1;
        } else if (isOnBorderRight()) {
            x = getWidth() - radius - 1;
        }

        if (isOnBorderTop()) {
            y = radius - 1;
        } else if (isOnBorderBottom()) {
            y = getHeight() - radius - 1;
        }

        return this;
    }
}

